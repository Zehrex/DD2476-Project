2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/server/netty/connections/NettyConnection.java
package com.nitro.application.communication.server.netty.connections;

import com.nitro.core.communication.connections.Connection;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.apache.commons.validator.routines.InetAddressValidator;

public class NettyConnection extends Connection {

    private Channel channel;

    public NettyConnection(Channel channel, String ip) {
        super(ip);

        this.channel = channel;
    }

    public void init() {

    }

    public void dispose() {
        if(this.isDisposed()) return;

        super.dispose();

        if(this.channel != null) {
            if(this.channel.isOpen()) this.channel.close();

            this.channel = null;
        }
    }

    public void write(ByteBuf buffer) {
        if(this.isDisposed() || (buffer == null)) return;

        this.channel.writeAndFlush(buffer);
    }

    public static String getIpAddress(Channel channel) {
        String data = channel.remoteAddress().toString().replace("/", "");
        String[] ipData = data.split(":");

        InetAddressValidator validator = InetAddressValidator.getInstance();

        if(validator.isValidInet4Address(ipData[0])) {
            return ipData[0];
        } else {
            String ip = data.replace(":" + ipData[ipData.length - 1], "");

            if(validator.isValidInet6Address(ip)) {
                return ip;
            }
        }

        return null;
    }
}
