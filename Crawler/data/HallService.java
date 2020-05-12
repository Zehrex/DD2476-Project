1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/HallService.java
package com.game.service;

import com.alibaba.fastjson.JSONObject;
import com.game.common.base.BaseService;
import com.game.core.annotation.MessageCommandAnnotation;
import com.game.core.message.Request;

/**
 * @Author: wx
 * @Date: 上午 11:06 2020/1/2 0002
 * @Desc:
 * @version:
 */
public interface HallService extends BaseService{

    /**
     * @Author: @
     * @Desc: 加入大厅
     * @Date: 上午 11:44 2020/1/2 0002
     * @Param
     */
    @MessageCommandAnnotation(cmd = 1002)
    void joinHall(Request request, JSONObject jsonObject);

}
