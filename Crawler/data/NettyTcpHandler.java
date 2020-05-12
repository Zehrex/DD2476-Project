6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/tcp/NettyTcpHandler.java
package com.github.taoroot.taoiot.netty.tcp;

import com.github.taoroot.taoiot.netty.NettyUtil;
import com.github.taoroot.taoiot.netty.tcp.core.AbstractProtocolBody;
import com.github.taoroot.taoiot.netty.tcp.core.PackageHandler;
import com.github.taoroot.taoiot.netty.tcp.core.PackageHandlerProcessor;
import com.github.taoroot.taoiot.netty.tcp.core.ProtocolBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author : taoroot
 * Date: 2019/9/12
 */
@Log4j2
@AllArgsConstructor
public class NettyTcpHandler extends SimpleChannelInboundHandler<ProtocolBody> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.debug("{} active", NettyUtil.getChannelName(ctx.channel()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("{}", NettyUtil.getChannelName(ctx.channel()), cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtocolBody basePackage) throws Exception {
        PackageHandler<? extends ProtocolBody> handler = PackageHandlerProcessor.getHandler(basePackage.getClass());
        AbstractProtocolBody abp = (AbstractProtocolBody) basePackage;
        Channel channel = channelHandlerContext.channel();
        log.debug("{}{}-{}", NettyUtil.getChannelName(channel), abp.getPackTypeHexStr(), abp.getPackTypeDesc());
        handler.process0(basePackage);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Channel channel = ctx.channel();
        log.debug("{} inactive", NettyUtil.getChannelName(channel));
    }
}
