2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/codec/evawire/EvaWireFormat.java
package com.nitro.application.communication.codec.evawire;

import com.nitro.core.communication.codec.Byte;
import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.codec.Short;
import com.nitro.core.communication.messages.IMessageDataWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

public class EvaWireFormat implements ICodec {

    public ByteBuf encode(int header, Object[] messages) {
        ByteBuf buffer = Unpooled.buffer();

        buffer.writeInt(0).writeShort(header);

        for(Object message : messages) {
            if(message instanceof Byte) {
                buffer.writeByte(((Byte) message).getValue());

                continue;
            }

            if(message instanceof Boolean) {
                buffer.writeBoolean((Boolean) message);

                continue;
            }

            if(message instanceof Short) {
                buffer.writeShort(((Short) message).getValue());

                continue;
            }

            if(message instanceof Integer) {
                buffer.writeInt((Integer) message);

                continue;
            }

            if(message instanceof String) {
                buffer.writeShort(message.toString().length());
                buffer.writeBytes(message.toString().getBytes());

                continue;
            }
        }

        buffer.setInt(0, (buffer.writerIndex() - 4));

        return buffer;
    }

    public List<IMessageDataWrapper> decode(ByteBuf buffer) {
        List<IMessageDataWrapper> wrappers = new ArrayList<>();

        while(true) {
            if(buffer.readableBytes() < 6) return wrappers;

            int length = buffer.readInt();

            if(length < 2) return wrappers;

            ByteBuf extracted = buffer.readBytes(length);

            wrappers.add(new EvaWireDataWrapper(extracted.readShort(), extracted));
        }
    }
}
