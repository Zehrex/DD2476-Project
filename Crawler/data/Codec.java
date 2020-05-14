23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/codec/Codec.java
package com.takiku.im_lib.codec;

import com.google.protobuf.MessageLiteOrBuilder;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

public interface Codec  {

    MessageToMessageEncoder<MessageLiteOrBuilder> EnCoder();
    MessageToMessageDecoder<ByteBuf> DeCoder();
}
