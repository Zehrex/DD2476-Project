6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/mp/MpAutoConfiguration.java
package com.github.taoroot.taoiot.mp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Configuration
@Import(MpEndpoint.class)
@ComponentScan("com.github.taoroot.taoiot.mp.handler")
public class MpAutoConfiguration {
}
