6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/SecurityAutoConfiguration.java
package com.github.taoroot.taoiot.security;

import cn.hutool.core.util.CharsetUtil;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.github.taoroot.taoiot.security.filter.JwtAuthenticationFilter;
import com.github.taoroot.taoiot.security.service.DbUserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Log4j2
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
@Import({DbUserDetailsServiceImpl.class, SecurityEndpoint.class, SecurityConfigure.class, JwtAuthenticationFilter.class})
public class SecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 微信公众号API的Service
     */
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(securityProperties.getWx().getAppId());
        log.debug("mp appid: {}", securityProperties.getWx().getAppId());
        wxMpConfigStorage.setSecret(securityProperties.getWx().getAppSecret());
        wxMpConfigStorage.setToken(securityProperties.getWx().getToken());
        wxMpConfigStorage.setAesKey(securityProperties.getWx().getAesKey());
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxMpService;
    }

    /**
     * 支付宝客户端API
     */
    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                securityProperties.getAli().getAppId(),
                securityProperties.getAli().getPrivateKey(),
                "json",
                CharsetUtil.UTF_8,
                securityProperties.getAli().getPublicKey(),
                "RSA2"
        );
    }

    /**
     * 密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
