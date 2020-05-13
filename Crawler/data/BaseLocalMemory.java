1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/common/base/BaseLocalMemory.java
package com.game.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date: 下午 5:57 2019/12/27 0027
 * @Desc: 本地缓存数据
 * @version:
 */
@Slf4j
@Component
public abstract class BaseLocalMemory extends BaseConfig {

    public static BaseLocalMemory baseLocalMemory = new BaseLocalMemory() {
        @Override
        public void detection() {
            log.info("初始化handler游戏配置！");
        }
    };

    @Async
    public abstract void detection();
}
