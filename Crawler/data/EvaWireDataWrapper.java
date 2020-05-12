2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/codec/evawire/EvaWireDataWrapper.java
package com.nitro.application.communication.codec.evawire;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class EvaWireDataWrapper implements IMessageDataWrapper {

    private int header;
    private ByteBuf buffer;

    public EvaWireDataWrapper(int header, ByteBuf buffer) {
        this.header = header;
        this.buffer = buffer;
    }

    public void dispose() {
        if(this.buffer != null) {
            this.buffer.release();

            this.buffer = null;
        }
    }

    public int readByte() {
        return this.buffer.readByte();
    }

    public ByteBuf readBytes(int length) {
        return this.buffer.readBytes(length);
    }

    public boolean readBoolean() {
        return (this.buffer.readByte() == 1);
    }

    public int readShort() {
        return this.buffer.readShort();
    }

    public int readInt() {
        return this.buffer.readInt();
    }

    public String readString() {
        return this.readBytes(this.readShort()).toString(Charset.defaultCharset());
    }

    public int getHeader() {
        return this.header;
    }

    public boolean bytesAvailable() {
        return (this.buffer.readableBytes() > 0);
    }
}
