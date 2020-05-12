6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/mqtt/impl/MqttConnectHandler.java
package com.github.taoroot.taoiot.netty.mqtt.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.github.taoroot.taoiot.netty.NettyUtil;
import com.github.taoroot.taoiot.netty.mqtt.MqttHandler;
import com.github.taoroot.taoiot.netty.mqtt.NettyMqttHandler;
import com.github.taoroot.taoiot.security.SecurityUser;
import com.github.taoroot.taoiot.security.SecurityUserDetailsService;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author : zhiyi
 * Date: 2020/5/6
 */
@Log4j2
@Component
@AllArgsConstructor
public class MqttConnectHandler implements MqttHandler<MqttConnectMessage> {

    private final SecurityUserDetailsService securityUserDetailsService;

    @Override
    public void process(Channel channel, MqttConnectMessage msg) {
        // 消息解码器出现异常
        if (msg.decoderResult().isFailure()) {
            Throwable cause = msg.decoderResult().cause();
            log.error(cause);
            if (cause instanceof MqttUnacceptableProtocolVersionException) {
                MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                        new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                        new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_UNACCEPTABLE_PROTOCOL_VERSION, false), null);
                channel.writeAndFlush(connAckMessage);
                log.error("不支持的协议版本");
                channel.close();
                return;
            } else if (cause instanceof MqttIdentifierRejectedException) {
                log.error("不合格的clientId");
                MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                        new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                        new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED, false), null);
                channel.writeAndFlush(connAckMessage);
                channel.close();
                return;
            }
            channel.close();
            return;
        }

        // clientId为空或null的情况, 这里要求客户端必须提供clientId, 不管cleanSession是否为1, 此处没有参考标准协议实现
        if (StrUtil.isBlank(msg.payload().clientIdentifier())) {
            MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                    new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                    new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED, false), null);
            channel.writeAndFlush(connAckMessage);
            log.error("无");
            channel.close();
            return;
        }

        // 用户名和密码验证, 这里要求客户端连接时必须提供用户名和密码, 不管是否设置用户名标志和密码标志为1, 此处没有参考标准协议实现
        String username = msg.payload().userName();
        String password = msg.payload().passwordInBytes() == null ? null : new String(msg.payload().passwordInBytes(), CharsetUtil.UTF_8);

        SecurityUser userDetails = (SecurityUser) securityUserDetailsService.loadUserByUserId(Integer.parseInt(username));

        if (!login(msg, password, userDetails)) {
            MqttConnAckMessage connAckMessage = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                    new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                    new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD, false), null);
            channel.writeAndFlush(connAckMessage);
            log.error("密码错误");
            channel.close();
            return;
        }

        NettyUtil.setName(channel, msg.payload().clientIdentifier());
        NettyUtil.setUser(channel, userDetails);

        NettyMqttHandler.channels.add(channel);

        MqttConnAckMessage okResp = (MqttConnAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, false), null);
        log.info("链接成功");
        channel.writeAndFlush(okResp);
    }

    private boolean login(MqttConnectMessage msg, String password, SecurityUser userDetails) {
        return userDetails != null && userDetails.getToken().equals(password)
                && Validator.isGeneral(msg.payload().clientIdentifier());
    }

}
