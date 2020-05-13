1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/message/RequestMessageByte.java
package com.game.core.message;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wx
 * @Date: 下午 5:22 2020/1/3 0003
 * @Desc:
 * @version:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestMessageByte {

    private Request request;

    private ByteBuf byteBuf;
}
