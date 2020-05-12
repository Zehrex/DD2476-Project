1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/common/connst/RedisKey.java
package com.game.common.connst;

import lombok.Getter;

/**
 * @Author: wx
 * @Date: 下午 2:04 2019/12/31 0031
 * @Desc:
 * @version:
 */
@Getter
public enum  RedisKey {

    /**
     * 渠道ID集合
     */
    CHANNEL_IDS("api_com:channel_ids","渠道ID集合"),

    /**
     * 游戏配置集合
     */
    GAME_CONFIGS("api_com:game_configs:","游戏地址配置集合"),

    /**
     * 游戏奖池配置     */
    JACK_POT("api_com:game_jack","游戏奖池配置"),

    /**
     * 游戏配置变更通知
     */
    GAME_ROOM_CONFIG("api_com:game_room_config","游戏配置变更通知"),

    /**
     * 用户上线状态
     */
    USER_ONLINE("api_com:cs_userId_online","用户上线状态"),

    /**
     * 游戏地址配置集合
     */
    SERVER_CONFIGS("api_com:server_configs","渠道ID集合");




    private String key;
    private String name;

    RedisKey(String key, String name ) {
        this.key = key;
        this.name = name;
    }
}
