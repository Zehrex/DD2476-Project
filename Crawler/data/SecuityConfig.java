137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-admin/src/main/java/com/java2nb/common/config/SecuityConfig.java
package com.java2nb.common.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@Configuration
public class SecuityConfig {


}
