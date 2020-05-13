1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/logic/HandlerDispatcher.java
package com.game.socket.logic;

import com.game.common.connst.Const;
import com.game.core.message.RequestMessageByte;
import com.game.core.message.RequestMessageData;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date: 下午 5:23 2020/1/2 0002
 * @Desc:
 * @version:
 */
@Slf4j
@Component
public class HandlerDispatcher extends DefaultThreadFactory implements Runnable {

    private static final String QUEUE = "Queue";
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1024), new DefaultThreadFactory(QUEUE), new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private MessageWorkerData messageWorkerData;

    /**
     * 消息队列
     */
    @Getter
    private MessageQueue messageQueue = new MessageQueue();

    public HandlerDispatcher() {
        super(QUEUE);
        log.info("定时线程执行");
    }

    @Override
    public void run() {
        log.info("执行线程池");
        Queue<RequestMessageByte> requestQueue = messageQueue.getRequestQueue();
        if (Objects.nonNull(requestQueue) && (requestQueue.size() > Const.Number.ZERO)) {
            threadPoolExecutor.execute(new MessageWorkerByte(messageQueue));
        }
        Queue<RequestMessageData> requestQueueData = messageQueue.getRequestQueueData();
        if (Objects.nonNull(requestQueueData) && (requestQueueData.size() > Const.Number.ZERO)) {
            threadPoolExecutor.execute(new MessageWorkerData(messageQueue));
        }
    }
}
