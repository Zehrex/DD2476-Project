1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/logic/MessageQueue.java
package com.game.socket.logic;

import com.game.core.message.RequestMessageByte;
import com.game.core.message.RequestMessageData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: wx
 * @Date: 下午 5:05 2020/1/2 0002
 * @Desc: 消息队列
 * @version:
 */
@Data
@Slf4j
@Component
public class MessageQueue{

    private Queue<RequestMessageByte> requestQueue = new ConcurrentLinkedQueue<>();


    private Queue<RequestMessageData> requestQueueData = new ConcurrentLinkedQueue<>();

    /**
     * @Author: @
     * @Desc: 添加二进制消息
     * @Date: 下午 7:47 2020/1/3 0003
     * @Param
     */
    public synchronized boolean add(RequestMessageByte requestMessage)
    {
        if(null==this.requestQueue){
            return false;
        }
        return this.requestQueue.add(requestMessage);
    }
    
    /**
     * @Author: @
     * @Desc: 添加json消息
     * @Date: 下午 7:46 2020/1/3 0003
     * @Param
     */
    public synchronized boolean add(RequestMessageData requestMessageData)
    {
        log.info("当前队列里面的数量: 开始:{}",requestQueueData.stream().count());
        if(null==this.requestQueueData){
            return false;
        }
        boolean add = requestQueueData.add(requestMessageData);
        log.info("当前队列里面的数量: 结束:{}",requestQueueData.stream().count());
        return add;
    }

}
