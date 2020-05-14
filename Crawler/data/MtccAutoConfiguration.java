11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-spring-cloud-starter/src/main/java/com/yf/mtcc/springcloud/starter/configuration/MtccAutoConfiguration.java
package com.yf.mtcc.springcloud.starter.configuration;

import com.yf.mtcc.core.bootstrap.MtccBootstap;
import com.yf.mtcc.core.service.MtccBootstrapService;
import com.yf.mtcc.springcloud.starter.config.MtccProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.yf.mtcc"})
public class MtccAutoConfiguration {

    @Autowired
    private MtccProperties properties;

    @Bean
    @Qualifier("mtccBootstrap")
    public MtccBootstap mtccBootstap(MtccBootstrapService mtccBootstrapService) {
        MtccBootstap bootstap = new MtccBootstap(properties, mtccBootstrapService);
        return bootstap;
    }
}
