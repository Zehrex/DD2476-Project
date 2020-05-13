6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/SecurityProperties.java
package com.github.taoroot.taoiot.security;

import cn.hutool.core.util.ReUtil;
import com.github.taoroot.taoiot.common.Const;
import com.github.taoroot.taoiot.security.annotation.NotAuth;
import com.github.taoroot.taoiot.security.annotation.ValidateCode;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Log4j2
@Data
@ConfigurationProperties(prefix = Const.PREFIX + ".security")
public class SecurityProperties implements InitializingBean {

    /**
     * 数据库表名
     */
    private String tableName = "user";

    /**
     * 开放接口
     */
    private List<String> permitAllUrls = new ArrayList<>();

    /**
     * 验证码接口
     */
    private List<String> validateCodeUrls = new ArrayList<>();

    /**
     * 支付宝参数
     */
    private AliPayApiConfig ali = new AliPayApiConfig();

    /**
     * 微信参数
     */
    private WxApiConfig wx = new WxApiConfig();

    /**
     * token参数
     */
    private TokenConfig token = new TokenConfig();

    @Resource
    private WebApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        // 一些默认开放的接口
        permitAllUrls.addAll(Arrays.asList(
                "/swagger-ui.html",
                "/v2/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/resources/**",
                "/*.html",
                "/*.htm",
                "/*.js",
                "/*.css")
        );
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        // 收集 NotAuth 注解的接口
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NotAuth.class))
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> permitAllUrls.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*"))));

            Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), NotAuth.class))
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> permitAllUrls.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*"))));

            Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ValidateCode.class))
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> validateCodeUrls.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*"))));

            Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), ValidateCode.class))
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> validateCodeUrls.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*"))));
        });

        log.info("permit all urls: {}", permitAllUrls);
        log.info("validate code urls: {}", validateCodeUrls);
    }

    /**
     * 微信配置
     */
    @Data
    public static class WxApiConfig {
        /**
         * 返回码
         */
        public final static String RESULT_CODE = "result_code";
        /**
         * 公众平台id
         */
        private String appId;
        /**
         * 公众平台密钥
         */
        private String appSecret;
        /**
         * auth2.0 地址
         */
        private String auth2Url;
        /**
         * 公众号服务器token
         */
        private String token;
        /**
         * 公众号服务器EncodingAESKey
         */
        private String aesKey;
        /**
         * 推送模板,测试账号需要提供,正常账号不需要配置
         */
        private String templateId;
    }

    /**
     * 支付宝配置
     */
    @Data
    public static class AliPayApiConfig {
        /**
         * appId
         */
        private String appId;
        /**
         * 密钥
         */
        private String privateKey;
        /**
         * 公钥
         */
        private String publicKey;
        /**
         * auth2.0 地址
         */
        private String auth2Url;
    }

    /**
     * Token 配置
     */
    @Data
    public static class TokenConfig {
        /**
         * 盐
         */
        private String secret = "taoiot";
        /**
         * 过期时常
         */
        private Long expiration = (long) (1000 * 60 * 60 * 24);
    }

}
