6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/tcp/codec/NettyPackageCodec.java
package com.github.taoroot.taoiot.netty.tcp.codec;

import com.github.taoroot.taoiot.netty.tcp.core.ProtocolBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author : zhiyi
 * Date: 2020/2/14
 * <p>
 * 编解码器
 */
public interface NettyPackageCodec {

    /**
     *  编码
     * @param ctx
     * @param message
     * @param out
     * @throws Exception
     */
    void encode(ChannelHandlerContext ctx, ProtocolBody message, ByteBuf out) throws Exception;


    /**
     * 解码
     * @param ctx
     * @param in
     * @param protocolBody
     * @return
     * @throws Exception
     */
    ProtocolBody decode(ChannelHandlerContext ctx, ByteBuf in, ProtocolBody protocolBody) throws Exception;
}
