1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/handler/impl/CoinRoomHandler.java
package com.game.handler.impl;

import com.game.core.annotation.MessageCommandAnnotation;
import com.game.core.message.RequestMessageData;
import com.game.handler.Handler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wx
 * @Date: 上午 10:33 2019/12/27 0027
 * @Desc: 加入场次
 * @version:
 */
@Slf4j
@MessageCommandAnnotation(cmd = 1001)
public class CoinRoomHandler extends Handler {

    @Override
    public void operation() {
        this.str = "CoinRoomHandler:{加入场次}";
    }

    @Override
    public void handlerMessage(RequestMessageData requestMessageData) {
        super.handlerMessage(requestMessageData);
        System.out.println("加入场次");
    }
}
