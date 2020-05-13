1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/logic/MessageWorkerByte.java
package com.game.socket.logic;

import com.game.coding.decoder.MessageDecoderFactoryApp;
import com.game.coding.encoder.MessageEncoderFactoryApp;
import com.game.core.message.*;
import com.game.handler.Handler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * @Author: wx
 * @Date: 下午 5:37 2020/1/2 0002
 * @Desc:
 * @version:
 */
@Component
public class MessageWorkerByte implements Runnable {

    private MessageQueue messageQueue;

    private RequestMessageByte requestMessage;

    private Handler handler = new Handler();

    @Autowired
    private MessageEncoderFactoryApp messageEncoderFactoryApp;

    @Autowired
    private MessageDecoderFactoryApp messageDecoderFactoryApp;

    public MessageWorkerByte(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
        this.requestMessage = messageQueue.getRequestQueue().poll();
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
        AbstractNetMessage abstractNetMessage = messageDecoderFactoryApp.parseMessage(requestMessage.getByteBuf());
        NetMessageHead messageHead = abstractNetMessage.getMessageHead();
        boolean verify = this.verify(messageHead);
        if(!verify) {
            Request request = requestMessage.getRequest();
            ChannelHandlerContext channelHandlerContext = request.getChannelHandlerContext();
            channelHandlerContext.writeAndFlush(new BinaryWebSocketFrame(messageEncoderFactoryApp.createByteBuf(new AbstractNetMessage())));
        }
        //todo 这里使用偏移量转为字符串
        byte[] bytes = abstractNetMessage.getMessageBody().getBytes();
        DataPacket dataPacket = DataPacket.builder().cmd(abstractNetMessage.getMessageHead().getCmd()).data(String.valueOf(bytes)).build();
        handler.handlerMessage(requestMessage.getRequest(),dataPacket);
    }
    
    /**
     * @Author: @
     * @Desc:  验证消息
     * @Date: 下午 12:12 2020/1/4 0004
     * @param: messageBody
     * @Return:
     */
    public boolean verify(NetMessageHead messageHead) {

        return true;
    }
}
