1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/coding/encoder/MessageEncoderFactoryApp.java
package com.game.coding.encoder;

import com.game.core.message.AbstractNetMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date: 下午 2:18 2019/12/26 0026
 * @Desc:  编码
 * @version:
 */
@Component
public class MessageEncoderFactoryApp {

    public ByteBuf createByteBuf(AbstractNetMessage netMessage){
        ByteBuf byteBuf = Unpooled.buffer(256);
        return byteBuf;
    }
}
