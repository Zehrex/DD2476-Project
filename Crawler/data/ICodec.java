2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/codec/ICodec.java
package com.nitro.core.communication.codec;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import io.netty.buffer.ByteBuf;

import java.util.List;

public interface ICodec {

    ByteBuf encode(int header, Object[] messages);
    List<IMessageDataWrapper> decode(ByteBuf buffer);
}
