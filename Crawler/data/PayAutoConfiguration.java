6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/PayAutoConfiguration.java
package com.github.taoroot.taoiot.pay;

import com.github.taoroot.taoiot.pay.service.impl.PayNotifyRecordServiceImpl;
import com.github.taoroot.taoiot.pay.service.impl.PayTradeOrderServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Configuration
@EnableConfigurationProperties(PayProperties.class)
@Import({PayNotifyRecordServiceImpl.class, PayNotifyEndpoint.class, PayTradeOrderServiceImpl.class})
@MapperScan("com.github.taoroot.taoiot.pay.mapper")
public class PayAutoConfiguration {

    @Resource
    private PayProperties payProperties;

}
