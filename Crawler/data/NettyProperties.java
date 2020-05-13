6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/NettyProperties.java
package com.github.taoroot.taoiot.netty;

import com.github.taoroot.taoiot.common.Const;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 服务配置
 *
 * @author zhiyi
 */
@Data
@ConfigurationProperties(prefix = Const.PREFIX + ".netty")
public class NettyProperties {

    /**
     * MQTT 接听端口
     */
    private int mqttPort = 1883;

    /**
     * 开启mqtt
     */
    private boolean enableMqtt = true;

    /**
     * TCP 接听端口
     */
    private int tcpPort = 1996;

    /**
     * 开启Mqtt
     */
    private boolean enableTcp = true;
}
