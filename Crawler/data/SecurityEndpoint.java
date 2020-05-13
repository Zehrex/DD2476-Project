6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/SecurityEndpoint.java
package com.github.taoroot.taoiot.security;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.github.taoroot.taoiot.common.BrowserUAEnum;
import com.github.taoroot.taoiot.common.R;
import com.github.taoroot.taoiot.security.annotation.NotAuth;
import com.github.taoroot.taoiot.security.exception.BaseException;
import com.github.taoroot.taoiot.security.filter.JwtUtil;
import com.github.taoroot.taoiot.security.filter.ValidateCodeUtil;
import com.github.taoroot.taoiot.security.service.DbUser;
import com.github.taoroot.taoiot.swagger.NotSwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author : zhiyi
 * Date: 2020/2/10
 */
@Log4j2
@Api(tags = "登录接口")
@AllArgsConstructor
@RestControllerAdvice
@RequestMapping
public class SecurityEndpoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final static TimedCache<String, String> UUID_TOKEN_CACHE = new TimedCache<>(1000 * 60 * 5);
    private final static String DEFAULT_UUID = "0";

    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;
    private final SecurityUserDetailsService securityUserDetailsService;
    private final WxMpService wxMpService;
    private final AlipayClient alipayClient;

    @NotAuth
    @GetMapping(value = "/code/image/{key}")
    @ApiOperation(value = "图形验证码", notes = "前端发送当前的时间戳(key), 后端返回一张图形码图片")
    public void getImage(HttpServletResponse response, @PathVariable("key") String key) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("cache-Control", "no-cache, must-revalidate");
        response.addHeader("cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            ValidateCodeUtil.getCaptcha(key).write(out);
            out.flush();
        }
    }

    @NotAuth
    @ApiOperation("账号密码登录")
    @PostMapping("/login")
    @ResponseBody
    public R<String> login(@RequestBody LoginParams params) {
        Authentication authentication;
        try {
            // 用户验证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            params.getUsername(),
                            params.getPassword())
            );
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new BaseException("用户名或密码错误", 402);
            } else if (e instanceof DisabledException) {
                throw new BaseException("账户被禁用", 402);
            } else if (e instanceof AccountExpiredException) {
                throw new BaseException("账户过期无法验证", 402);
            } else {
                throw new BaseException("账户被锁定,无法登录", 402);
            }
        }

        // 存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 返回 JWT TOKEN
        SecurityUser userDetail = (SecurityUser) authentication.getPrincipal();
        return R.ok(
                JwtUtil.create(
                        userDetail.getUsername(),
                        securityProperties.getToken().getSecret(),
                        securityProperties.getToken().getExpiration()
                )
        );
    }

    @ApiOperation("扫码登录")
    @GetMapping("/mp_callback/{uuid}")
    @SneakyThrows
    @ResponseBody
    @NotAuth
    public R<String> loginByPC(@PathVariable String uuid) {
        String token = UUID_TOKEN_CACHE.get(uuid);
        if (token == null) {
            return R.err();
        }
        return R.ok(token, "登录成功");
    }

    @ApiOperation("公众号登录")
    @GetMapping("/authorize")
    @SneakyThrows
    @NotAuth
    public String authorize(@RequestParam(value = "uuid", defaultValue = "0") String uuid, @RequestParam("returnUrl") String returnUrl, HttpServletRequest request) {
        String ua = request.getHeader(HttpHeaders.USER_AGENT);

        String chart = returnUrl.contains("?") ? "&" : "?";
        returnUrl += chart + "uuid=" + uuid;
        returnUrl = URLEncoder.encode(returnUrl, "UTF-8");

        String redirectUrl;
        // 微信
        if (ua.contains(BrowserUAEnum.WECHAT.getBrowserUserAgent())) {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(
                    securityProperties.getWx().getAuth2Url(),
                    WxConsts.OAuth2Scope.SNSAPI_BASE, returnUrl);
            return "redirect:" + redirectUrl;
        }
        // 支付宝
        redirectUrl = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=" + securityProperties.getAli().getAppId() +
                "&scope=auth_base" +
                "&redirect_uri=" + URLEncoder.encode(securityProperties.getAli().getAuth2Url(), "UTF-8") +
                // 支付宝也会解码一次, 被坑过一次
                "&state=" + URLEncoder.encode(returnUrl, "UTF-8");
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/wx/snsapi_base")
    @SneakyThrows
    @NotAuth
    @NotSwagger
    public String wxUserInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
        String openId = accessToken.getOpenId();
        Boolean subscribe = wxMpService.getUserService().userInfo(openId).getSubscribe();
        DbUser user = new DbUser();
        user.setUsername(openId);
        UserDetails userDetails = securityUserDetailsService.loadUserByWechat(openId);

        String token = JwtUtil.create(
                userDetails.getUsername(),
                securityProperties.getToken().getSecret(),
                securityProperties.getToken().getExpiration())
                + "&sub=" + subscribe;

        String uuid = HttpUtil.decodeParamMap(returnUrl, CharsetUtil.UTF_8).get("uuid");
        if (uuid != null && !uuid.equals(DEFAULT_UUID)) {
            UUID_TOKEN_CACHE.put(uuid, token);
        }

        String chart = returnUrl.contains("?") ? "&" : "?";
        return "redirect:" + returnUrl + chart + "token=" + token;
    }


    @GetMapping("/ali/auth_base")
    @NotAuth
    @NotSwagger
    public String aliUserInfo(@RequestParam("auth_code") String code, @RequestParam("state") String returnUrl) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = null;
        try {
            alipaySystemOauthTokenResponse = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        assert alipaySystemOauthTokenResponse != null;
        String openId = alipaySystemOauthTokenResponse.getUserId();

        UserDetails userDetails = securityUserDetailsService.loadUserByAlipay(openId);

        String uuid = HttpUtil.decodeParamMap(returnUrl, CharsetUtil.UTF_8).get("uuid");
        String token = JwtUtil.create(
                userDetails.getUsername(),
                securityProperties.getToken().getSecret(),
                securityProperties.getToken().getExpiration());

        if (uuid != null && !uuid.equals(DEFAULT_UUID)) {
            UUID_TOKEN_CACHE.put(uuid, token);
        }

        String chart = returnUrl.contains("?") ? "&" : "?";
        return "redirect:" + returnUrl + chart + "token=" + token;
    }


    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        SecurityUtil.writeToResponse(R.err(HttpStatus.HTTP_UNAUTHORIZED, "登录失败"), response);
    }

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        SecurityUtil.writeToResponse(R.err(HttpStatus.HTTP_UNAUTHORIZED, "无权访问"), response);
    }

    @ExceptionHandler(BaseException.class)
    public R<Integer> handleBaseException(BaseException e) {
        return R.err(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R<Integer> handlerNoFoundException(Exception e) {
        return R.err(HttpStatus.HTTP_NOT_FOUND, "路径不存在");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R<Integer> handleAuthorizationException(AccessDeniedException e) {
        return R.err(HttpStatus.HTTP_UNAUTHORIZED, "没有权限");
    }

    @ExceptionHandler(Exception.class)
    public R<Integer> handleException(Exception e) {
        return R.err(e.getMessage());
    }


    @Data
    static class LoginParams {
        private String username;
        private String password;
    }
}
