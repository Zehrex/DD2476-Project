1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/SimulationToMessage.java
package com.game.socket;

import com.game.handler.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wx
 * @Date: 上午 10:34 2019/12/27 0027
 * @Desc: 模拟发送消息
 * @version:
 */
public class SimulationToMessage {

    public static Map<Integer, Handler> map = new HashMap<>();

    public void toMessage() {
        Handler integer = map.get(1);
        integer.handlerMessage(null,null);
    }
}
