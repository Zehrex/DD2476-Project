1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/GameInitializer.java
package com.game.socket.netty;

/**
 * @Author: wx
 * @Date: 下午 5:44 2019/12/21 0021
 * @Desc:
 * @version:
 */
public interface GameInitializer {

    /**
     * init
     *
     * @throws Exception Throwable
     */
    default void init() throws Exception {
    }

    /**
     * destroy
     *
     * @throws Exception Throwable
     */
    default void destroy() throws Exception {
    }

    /**
     * order
     *
     * @return int
     */
    default int order() {
        return 0;
    }
}
