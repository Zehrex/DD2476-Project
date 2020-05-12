6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/tcp/codec/PackageEncoder.java
package com.github.taoroot.taoiot.netty.tcp.codec;

import com.github.taoroot.taoiot.netty.tcp.core.ProtocolBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * 编码
 */
@AllArgsConstructor
public class PackageEncoder extends MessageToByteEncoder<ProtocolBody> {

    private final NettyPackageCodec encoder;

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolBody protocolBody, ByteBuf out) throws Exception {
        encoder.encode(ctx, protocolBody, out);
    }
}
