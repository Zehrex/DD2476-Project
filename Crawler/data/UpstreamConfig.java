3
https://raw.githubusercontent.com/cyl2cyl/nginx_service_discovery/master/src/main/java/com/xm/service/discovery/config/UpstreamConfig.java
package com.xm.service.discovery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author caoyilong
 * @description
 * @package com.xm.service.discovery.pojo
 * @date 2020/05/12 11:51
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upstream")
public class UpstreamConfig {

    private Set<DiscoverConfig> lists;


}
