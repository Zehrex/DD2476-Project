1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_client/src/main/java/com/game/decoder/MessageDecoderFactory.java
package com.game.decoder;

import com.game.message.AbstractNetMessage;
import com.game.message.NetMessageBody;
import com.game.message.NetMessageHead;
import io.netty.buffer.ByteBuf;

/**
 * @Author: wx
 * @Date: 下午 9:05 2019/12/20 0020
 * @Desc: 解析
 * @version:
 */
public class MessageDecoderFactory {

    public AbstractNetMessage praseMessage(ByteBuf byteBuf) {
        NetMessageHead netMessageHead = new NetMessageHead();
        byteBuf.skipBytes(2);
        netMessageHead.setLength(byteBuf.readInt());
        netMessageHead.setVersion(byteBuf.readByte());

        AbstractNetMessage abstractNetMessage = new AbstractNetMessage();

        short cmd = byteBuf.readShort();
        netMessageHead.setCmd(cmd);
        netMessageHead.setSerial(byteBuf.readInt());

        NetMessageBody netMessageBody = new NetMessageBody();
        int byteLength = byteBuf.readableBytes();
        byte[] bytes = new byte[byteLength];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        netMessageBody.setBytes(bytes);

        abstractNetMessage.setNetMessageHead(netMessageHead);
        abstractNetMessage.setNetMessageBody(netMessageBody);
        return abstractNetMessage;
    }
}
