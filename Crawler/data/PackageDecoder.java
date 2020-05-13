6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/tcp/codec/PackageDecoder.java
package com.github.taoroot.taoiot.netty.tcp.codec;

import com.github.taoroot.taoiot.netty.tcp.core.AbstractProtocolBody;
import com.github.taoroot.taoiot.netty.tcp.core.PackageFactory;
import com.github.taoroot.taoiot.netty.tcp.core.ProtocolBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.log4j.Log4j2;

import java.nio.ByteOrder;

/**
 * 解码器
 */
@Log4j2
public class PackageDecoder extends LengthFieldBasedFrameDecoder {

    private final NettyPackageCodec codec;

    private final ByteOrder byteOrder;

    private final int typeFieldOffset;

    private final int typeFieldLength;

    public PackageDecoder(NettyPackageCodec codec, NettyCodecProperties properties, ByteOrder order) {
        this(
                order,
                properties.getMaxFrameLength(),
                properties.getLengthFieldOffset(),
                properties.getLengthFieldLength(),
                properties.getLengthAdjustment(),
                properties.getInitialBytesToStrip(),
                properties.isFailFast(),
                properties.getTypeFieldOffset(),
                properties.getTypeFieldLength(),
                codec);
    }

    public PackageDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset,
                          int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast,
                          int typeFieldOffset, int typeFieldLength, NettyPackageCodec codec) {
        super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
        this.typeFieldLength = typeFieldLength;
        this.typeFieldOffset = typeFieldOffset;
        this.codec = codec;
        this.byteOrder = byteOrder;
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        in.markReaderIndex();

        // 类型偏移量
        int type = (int) getUnadjustedFrameLength(in, typeFieldOffset, typeFieldLength, byteOrder);

        ProtocolBody protocolBody = PackageFactory.newInstance(type);

        if(protocolBody == null) {
            log.debug("not find type: {}", type);
            return null;
        }

        ((AbstractProtocolBody) protocolBody).setChannel(ctx.channel());

        in.resetReaderIndex();

        return codec.decode(ctx, in, protocolBody);
    }
}
