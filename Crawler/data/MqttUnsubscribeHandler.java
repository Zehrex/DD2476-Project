6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/mqtt/impl/MqttUnsubscribeHandler.java
package com.github.taoroot.taoiot.netty.mqtt.impl;

import com.github.taoroot.taoiot.netty.NettyUtil;
import com.github.taoroot.taoiot.netty.mqtt.MqttHandler;
import com.github.taoroot.taoiot.netty.mqtt.NettyMqttHandler;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : zhiyi
 * Date: 2020/5/7
 */
@Log4j2
@Component
public class MqttUnsubscribeHandler implements MqttHandler<MqttUnsubscribeMessage> {
    @Override
    public void process(Channel channel, MqttUnsubscribeMessage msg) {
        List<String> topicFilters = msg.payload().topics();
        String clientId = channel.attr(NettyUtil.NAME).get();
        for (String topicFilter : topicFilters) {
            ChannelGroup channels = NettyMqttHandler.TOPICS.get(topicFilter);
            if (channels != null) {
                channels.remove(channel);
            }
            log.debug("UNSUBSCRIBE - clientId: {}, topicFilter: {}", clientId, topicFilter);
        }
        MqttUnsubAckMessage unSubAckMessage = (MqttUnsubAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(msg.variableHeader().messageId()), null);
        channel.writeAndFlush(unSubAckMessage);
    }
}
