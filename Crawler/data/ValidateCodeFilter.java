6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/filter/ValidateCodeFilter.java
package com.github.taoroot.taoiot.security.filter;

import cn.hutool.http.HttpStatus;
import com.github.taoroot.taoiot.common.R;
import com.github.taoroot.taoiot.security.SecurityProperties;
import com.github.taoroot.taoiot.security.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码过滤器
 *
 * @author zhiyi
 */
@Component
@AllArgsConstructor
public class ValidateCodeFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        boolean match = securityProperties.getValidateCodeUrls()
                .stream()
                .anyMatch(requestMatcher -> new AntPathRequestMatcher(requestMatcher).matches(request));

        if (match) {
            String key = request.getParameter("key");
            String code = request.getParameter("code");
            boolean b = ValidateCodeUtil.verifyCaptcha(key, code);
            if (!b) {
                SecurityUtil.writeToResponse(R.err(HttpStatus.HTTP_UNAUTHORIZED, "验证码错误"), response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}