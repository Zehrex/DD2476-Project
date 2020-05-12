1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/logic/ScheduledThread.java
package com.game.socket.logic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date: 下午 4:52 2020/1/2 0002
 * @Desc:
 * @version:
 */
@Slf4j
@Component
public class ScheduledThread extends DefaultThreadFactory {

    @Autowired
    private HandlerDispatcher handlerDispatcher;

    private static final String MESSAGE = "Message";

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10, new DefaultThreadFactory(MESSAGE));

    public ScheduledThread() {
        super(MESSAGE);
    }

    /**
     * @Author: @
     * @Desc: 启动
     * @Date: 下午 5:02 2020/1/2 0002
     * @Param
     */
    public void start() {
        log.info("---");
        scheduledThreadPoolExecutor.scheduleAtFixedRate(handlerDispatcher, 0,10,TimeUnit.SECONDS);
    }

}
