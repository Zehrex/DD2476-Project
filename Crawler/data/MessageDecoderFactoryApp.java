1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/coding/decoder/MessageDecoderFactoryApp.java
package com.game.coding.decoder;

import com.game.core.message.AbstractNetMessage;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date: 下午 2:17 2019/12/26 0026
 * @Desc: 解码
 * @version:
 */
@Component
public class MessageDecoderFactoryApp {

    public AbstractNetMessage parseMessage(ByteBuf byteBuf) {

        return new AbstractNetMessage();
    }
}
