12
https://raw.githubusercontent.com/zhuchangwu/lawyer-lover-cloud-backend/master/lawyer-lover-main/src/main/java/com/changwu/config/FeignConfig.java
package com.changwu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
}
