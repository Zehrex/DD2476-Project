1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/timing/GameConfigSchedule.java
package com.game.core.timing;

import com.game.common.base.BaseLocalMemory;
import com.game.common.connst.RedisKey;
import com.game.core.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: wx
 * @Date: 下午 5:30 2019/12/31 0031
 * @Desc: 定时检测配置
 * @version:
 */
@Slf4j
@Component
public class GameConfigSchedule {

    private final ApplicationContext applicationContext;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    public GameConfigSchedule(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static final String LOCK_KEY = "gameConfigSchedule";

    public static boolean isRunning = false;

    /**
     * @Author: @
     * @Desc:
     * @Date: 下午 5:30 2019/12/31 0031
     * @Param 定时检测配置
     */
    @Scheduled(fixedDelay = 1000 * 60)
    public void run() {
        synchronized (LOCK_KEY) {
            if (isRunning) {
                return;
            } else {
                isRunning = true;
            }
        }

        List<String> list = redisUtil.getList(RedisKey.GAME_ROOM_CONFIG.getKey(), 0, 100);
        if(CollectionUtils.isEmpty(list)){
            return;
        }

        list.forEach(data->{
            redisUtil.remove(RedisKey.GAME_ROOM_CONFIG.getKey(), data);
        });

        Map<String, BaseLocalMemory> runnerMap = null;
        try {
            runnerMap = applicationContext.getBeansOfType(BaseLocalMemory.class);
            for (BaseLocalMemory value : runnerMap.values()) {
                log.info("所有集成该方法的人 value:{}",value.getClass());
                value.detection();
            }
        } catch (BeansException e) {
            e.printStackTrace();
        } finally {
            isRunning=false;
        }
    }
}