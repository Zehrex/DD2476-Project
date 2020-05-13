1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/logic/MessageWorkerData.java
package com.game.socket.logic;

import com.game.common.base.BaseLocalMemory;
import com.game.core.message.DataPacket;
import com.game.core.message.RequestMessageData;
import com.game.handler.Handler;
import com.game.handler.impl.CoinHallHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: wx
 * @Date: 下午 8:01 2020/1/3 0003
 * @Desc:
 * @version:
 */
@RestController
public class MessageWorkerData implements Runnable  {

    private MessageQueue messageQueue;

    private RequestMessageData requestMessageData;

    public MessageWorkerData(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
        this.requestMessageData = messageQueue.getRequestQueueData().poll();
    }

    @Override
    public void run() {
        handleMessageQueue();
    }

    /**
     * @Author: @
     * @Desc:  执行消息处理
     * @Date: 下午 5:39 2020/1/2 0002
     * @Param
     */
    private void handleMessageQueue() {
        DataPacket dataPacket = requestMessageData.getDataPacket();
        Map<Integer, Handler> handlerLocalMap = BaseLocalMemory.baseLocalMemory.getHandlerLocalMap();
        Handler handler = handlerLocalMap.get(dataPacket.getCmd());
        handler.handlerMessage(requestMessageData);
    }
}
