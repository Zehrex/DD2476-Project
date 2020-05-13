1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/WebSocketManager.java
package com.game.socket.netty;

import com.game.common.connst.RedisKey;
import com.game.core.message.Request;
import com.game.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: @
 * @Desc: 管理websocket
 * @Date: 上午 9:48 2020/1/6 0006
 * @Version: 0.1
 */
@Component
public class WebSocketManager {

    private static RedisUtil redisUtils;

    @Autowired
    public WebSocketManager(RedisUtil redisUtil) {
        WebSocketManager.redisUtils = redisUtil;
    }

    /**
     * 保存所有的websocket
     */
    private static final ConcurrentHashMap<String, Request> requestConcurrentHashMap = new ConcurrentHashMap<>();

    /**
     * @Author: @
     * @Desc: 玩家绑定上线
     * @Date: 上午 9:57 2020/1/6 0006
     * @param:
     * @Return:
     */
    public static void bindWebSocket(Integer userId, Request request, Integer gameId, Integer gameNum) {
        String newUserId = String.valueOf(userId);
        requestConcurrentHashMap.put(newUserId, request);
        request.setUserId(userId);
        String StringOldAddress = redisUtils.getHash(RedisKey.USER_ONLINE.getKey(), newUserId);
        if (!StringUtils.isEmpty(StringOldAddress) || !StringOldAddress.equals(getGameServerStr(gameId, gameNum))) {
            //todo 玩家下线
        }
        redisUtils.putMap(RedisKey.USER_ONLINE.getKey(), newUserId, getGameServerStr(gameId, gameNum));
    }
    
    /**
     * @Author: @
     * @Desc: Request
     * @Date: 上午 10:50 2020/1/6 0006
     * @param:
     * @Return:
     */
    public static Request getWebSocket(int userId) {
        return requestConcurrentHashMap.get(String.valueOf(userId));
    }
    
    /**
     * @Author: @
     * @Desc: 玩家下线
     * @Date: 上午 11:24 2020/1/6 0006
     * @param:
     * @Return:
     */
    public static void remove(Request request) {
        Integer userId = request.getUserId();
        //删除用户绑定状态
        requestConcurrentHashMap.remove(String.valueOf(userId));
        //删除用户在线状态
        redisUtils.deleteMap(RedisKey.USER_ONLINE.getKey(),String.valueOf(userId));
        //玩家离线推送流水

    }

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 10:10 2020/1/6 0006
     * @param:
     * @Return:
     */
    public static String getGameServerStr(Integer gameId, Integer gameNum) {
        return gameId + ":" + gameNum;
    }
}
