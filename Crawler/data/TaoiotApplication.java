6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/TaoiotApplication.java
package com.github.taoroot.taoiot;

import com.github.taoroot.taoiot.mp.MpAutoConfiguration;
import com.github.taoroot.taoiot.netty.NettyAutoConfiguration;
import com.github.taoroot.taoiot.pay.PayAutoConfiguration;
import com.github.taoroot.taoiot.security.SecurityAutoConfiguration;
import com.github.taoroot.taoiot.swagger.SwaggerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Idea 开源版(别问,问就是穷)不支持Spring提示, 采用手动导入的方式,只有业务代码才拿来扫描
 *
 * @author zhiyi
 */
@SpringBootApplication(scanBasePackages = "com.github.taoroot.taoiot.service")
@Import({PayAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        NettyAutoConfiguration.class,
        SwaggerAutoConfiguration.class,
        MpAutoConfiguration.class
})
public class TaoiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaoiotApplication.class, args);
    }

}
