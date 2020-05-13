6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/mp/handler/MpTextHandler.java
package com.github.taoroot.taoiot.mp.handler;

import com.github.taoroot.taoiot.mp.MpHandler;
import com.github.taoroot.taoiot.netty.mqtt.NettyMqttHandler;
import com.github.taoroot.taoiot.security.SecurityUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.mqtt.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 文字消息
 *
 * @author : zhiyi
 * Date: 2020/5/7
 */
@Log4j2
@Component(WxConsts.XmlMsgType.TEXT)
@AllArgsConstructor
public class MpTextHandler implements MpHandler {

    private final MpMsgService mpMsgService;

    @Override
    public String process(WxMpXmlMessage inMessage) throws Exception {
        String result = "发送成功";
        String content = inMessage.getContent();

        // taoiot:get_token
        if (StringUtils.startsWithIgnoreCase(content, "taoiot")) {
            // 获取token
            if ("taoiot:get_token".equals(content)) {
                return "userId: " + SecurityUtil.getUser().getId() + ", token: " + SecurityUtil.getUser().getToken();
            }
        }

        // 格式: mqtt:${topic}:${context}
        if (StringUtils.startsWithIgnoreCase(content, "mqtt")) {
            mqttProcess(content);
        }
        return result;
    }

    private void mqttProcess(String content) {
        Integer userId = SecurityUtil.getUserId();
        String[] split = content.split(":");
        if (split.length > 2) {
            String topic = split[1];
            if ("/".startsWith(topic)) {
                topic = userId + topic;
            } else {
                topic = userId + "/" + topic;
            }
            String msg = content.substring(StringUtils.ordinalIndexOf(content, ":", 2) + 1);
            MqttMessage mqttMessage = MqttMessageFactory.newMessage(
                    new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_MOST_ONCE, false, 0),
                    new MqttPublishVariableHeader(topic, 0),
                    Unpooled.buffer().writeBytes(msg.getBytes()));
            ChannelGroup channelGroup = NettyMqttHandler.TOPICS.get(topic);
            if (channelGroup != null) {
                channelGroup.writeAndFlush(mqttMessage);
            }
            log.debug("PUBLISH - topic: {}, Qos: {}, channels: {}",
                    topic,
                    MqttQoS.AT_MOST_ONCE,
                    channelGroup == null ? 0 : channelGroup.size());
        }
    }
}
