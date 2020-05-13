2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/server/netty/codec/NettyDecoder.java
package com.nitro.application.communication.server.netty.codec;

import com.nitro.application.communication.server.netty.NettyServer;
import com.nitro.core.communication.messages.IMessageDataWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {

    private NettyServer nettyServer;

    public NettyDecoder(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
        int delimiter = buffer.readByte();
        buffer.resetReaderIndex();

        if(delimiter == 60) {
            String policyString = "<?xml version=\"1.0\"?>\r\n"
                    + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n"
                    + "<cross-domain-policy>\r\n"
                    + "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n"
                    + "</cross-domain-policy>\0)";

            ChannelFuture future = ctx.channel().writeAndFlush(Unpooled.copiedBuffer(policyString.getBytes()));
            future.addListener(ChannelFutureListener.CLOSE);
        } else {
            List<IMessageDataWrapper> wrappers = this.nettyServer.getCodec().decode(buffer);

            if((wrappers == null) || (wrappers.size() < 1)) return;

            out.addAll(wrappers);
        }
    }
}
