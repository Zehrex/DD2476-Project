1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/common/base/BaseService.java
package com.game.common.base;

import com.game.core.message.AbstractNetMessage;
import com.game.core.message.DataPacket;
import com.game.core.message.Request;
import com.game.entity.SdkUser;
import com.game.entity.dto.Player;

/**
 * @Author: wx
 * @Date: 上午 11:25 2020/1/2 0002
 * @Desc:
 * @version:
 */
public interface BaseService {

    /**
     * @Author: @
     * @Desc: 反射进来
     * @Date: 上午 11:18 2020/1/2 0002
     * @Param
     */
   default void invokeMethod(short cmd, Request request, DataPacket dataPacket){

   };


}
