6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/mqtt/impl/MqttSubscribeHandler.java
package com.github.taoroot.taoiot.netty.mqtt.impl;

import cn.hutool.core.util.StrUtil;
import com.github.taoroot.taoiot.netty.NettyUtil;
import com.github.taoroot.taoiot.netty.mqtt.MqttHandler;
import com.github.taoroot.taoiot.netty.mqtt.NettyMqttHandler;
import com.github.taoroot.taoiot.security.SecurityUser;
import io.netty.channel.Channel;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhiyi
 * Date: 2020/5/6
 */
@Log4j2
@Component
public class MqttSubscribeHandler implements MqttHandler<MqttSubscribeMessage> {
    @Override
    public void process(Channel channel, MqttSubscribeMessage msg) {
        SecurityUser user = NettyUtil.getUser(channel);
        if (user == null) {
            channel.close();
            return;
        }

        List<MqttTopicSubscription> topicSubscriptions = msg.payload().topicSubscriptions();
        if (this.validTopicFilter(topicSubscriptions)) {
            List<Integer> qos = new ArrayList<>();
            topicSubscriptions.forEach(topicSubscription -> {
                // 不允许订阅其他用户主题
                String userId = topicSubscription.topicName().split("/")[0];
                if (!user.getId().equals(Integer.parseInt(userId))) {
                    channel.close();
                    return;
                }
                qos.add(topicSubscription.qualityOfService().value());
                NettyMqttHandler.TOPICS.putIfAbsent(topicSubscription.topicName(), new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));
                NettyMqttHandler.TOPICS.get(topicSubscription.topicName()).add(channel);
            });
            MqttSubAckMessage subAckMessage = (MqttSubAckMessage) MqttMessageFactory.newMessage(
                    new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                    MqttMessageIdVariableHeader.from(msg.variableHeader().messageId()),
                    new MqttSubAckPayload(qos));
            channel.writeAndFlush(subAckMessage);
            log.debug("SUBSCRIBE - clientId: {}, topFilter: {}, QoS: {}", NettyUtil.getName(channel), topicSubscriptions, qos);
        } else {
            channel.close();
        }
    }

    private boolean validTopicFilter(List<MqttTopicSubscription> topicSubscriptions) {
        for (MqttTopicSubscription topicSubscription : topicSubscriptions) {
            String topicFilter = topicSubscription.topicName();
            if (StrUtil.startWith(topicFilter, '#')
                    || StrUtil.endWith(topicFilter, '/') || !StrUtil.contains(topicFilter, '/')
                    || StrUtil.startWith(topicFilter, '+')) {
                return false;
            }
            if (StrUtil.contains(topicFilter, '#')) {
                // 不是以/#字符串结尾的订阅按非法订阅处理
                if (!StrUtil.endWith(topicFilter, "/#")) {
                    return false;
                }
                // 如果出现多个#符号的订阅按非法订阅处理
                if (StrUtil.count(topicFilter, '#') > 1) {
                    return false;
                }
            }
            if (StrUtil.contains(topicFilter, '+')) {
                //如果+符号和/+字符串出现的次数不等的情况按非法订阅处理
                if (StrUtil.count(topicFilter, '+') != StrUtil.count(topicFilter, "/+")) {
                    return false;
                }
            }
        }
        return true;
    }

}
