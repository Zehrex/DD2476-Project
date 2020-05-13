2
https://raw.githubusercontent.com/jiangvin/webtank/master/websocket/src/main/java/com/integration/socket/configuration/ResourceConfiguration.java
package com.integration.socket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 蒋文龙(Vin)
 * @description
 * @date 2020/4/23
 */

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/");
    }
}
