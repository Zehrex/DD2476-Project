1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/common/base/BaseConfig.java
package com.game.common.base;

import com.game.entity.GameConfig;
import com.game.entity.GameJack;
import com.game.entity.ServerInfoConfig;
import com.game.entity.dto.HallGameMgr;
import com.game.handler.Handler;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wx
 * @Date: 下午 5:58 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Data
public abstract class BaseConfig {

    /**房间配置*/
    private Map<Integer, GameConfig> gameConfigMap = new HashMap<>();

    /**游戏配置*/
    private ServerInfoConfig serverInfo = new ServerInfoConfig();

    /**奖金池配置*/
    private Map<Integer, GameJack> gameJackMap = new HashMap<>();

    /**游戏内部配置*/
    private HallGameMgr hallGameMgr = new HallGameMgr();

    /** handler配置信息 */
    private Map<Integer, Handler> handlerLocalMap = new HashMap<Integer, Handler>();

    /**方法协议号配置*/
    private Map<Integer, Method> methodLocalMap = new HashMap<>();

}
