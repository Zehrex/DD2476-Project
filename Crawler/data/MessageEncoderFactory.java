1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_client/src/main/java/com/game/encoder/MessageEncoderFactory.java
package com.game.encoder;

import com.game.message.AbstractNetMessage;
import com.game.message.NetMessageBody;
import com.game.message.NetMessageHead;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author: wx
 * @Date: 上午 10:54 2019/12/21 0021
 * @Desc:
 * @version:
 */
public class MessageEncoderFactory {

    public ByteBuf createByteBuf(AbstractNetMessage netProtoBufMessage) {
        ByteBuf byteBuf = Unpooled.buffer(256);

        NetMessageHead netMessageHead1 = new NetMessageHead();
        //编写head
        NetMessageHead netMessageHead = netProtoBufMessage.getNetMessageHead();
        byteBuf.writeShort(netMessageHead.getHead());
        //长度
        byteBuf.writeInt(0);
        //设置内容
        byteBuf.writeByte(netMessageHead.getVersion());
        byteBuf.writeShort(netMessageHead.getCmd());
        byteBuf.writeInt(netMessageHead.getSerial());

        NetMessageBody netMessageBody = netProtoBufMessage.getNetMessageBody();
        byteBuf.writeBytes(netMessageBody.getBytes());

        //设置长度
        int skip = 6;
        int length = byteBuf.readableBytes() - skip;
        byteBuf.setInt(2, length);
        //而 slice 操作可以将一个 ByteBuf 切片 为多个共享一个存储区域的 ByteBuf 对象.
        byteBuf.slice();
        return byteBuf;
    }
}
