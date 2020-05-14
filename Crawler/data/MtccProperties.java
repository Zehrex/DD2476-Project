11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-spring-cloud-starter/src/main/java/com/yf/mtcc/springcloud/starter/config/MtccProperties.java
package com.yf.mtcc.springcloud.starter.config;

import com.yf.mtcc.common.config.MtccConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Component("tccConfig")
@ConfigurationProperties(prefix = "mtcc", ignoreInvalidFields = true)
public class MtccProperties extends MtccConfig {
}
