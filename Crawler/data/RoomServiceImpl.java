1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/impl/RoomServiceImpl.java
package com.game.service.impl;

import com.game.core.message.DataPacket;
import com.game.core.message.Request;
import com.game.service.RoomService;
import org.springframework.stereotype.Service;

/**
 * @Author: wx
 * @Date: 上午 11:07 2020/1/2 0002
 * @Desc:
 * @version:
 */
@Service
public class RoomServiceImpl  extends abstractService implements RoomService{

    @Override
    public void invokeMethod(short cmd, Request request, DataPacket dataPacket) {
        super.invokeMethod(cmd, request, dataPacket);
    }
}
