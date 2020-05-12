1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/handler/HandlerService.java
package com.game.handler;

import com.game.core.message.AbstractNetMessage;
import com.game.core.message.DataPacket;
import com.game.core.message.Request;
import com.game.core.message.RequestMessageData;

/**
 * @Author: wx
 * @Date: 上午 11:35 2019/12/27 0027
 * @Desc:
 * @version:
 */
public interface HandlerService {

    /**
     * @Author: @
     * @Desc: 接收二进制消息
     * @Date: 上午 10:25 2019/12/27 0027
     * @Param
     */
    void handlerMessage(Request request, DataPacket dataPacket);

    /**
     * @Author: @
     * @Desc:
     * @Date: 下午 6:10 2020/1/3 0003
     * @Param
     */
    void handlerMessage(RequestMessageData requestMessageData);

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 11:39 2019/12/27 0027
     * @Param
     */
    default void operation() {

    }

    ;

}
