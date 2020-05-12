1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/WebSocketFrameServerHandler.java
package com.game.socket.netty;

import com.alibaba.fastjson.JSONObject;
import com.game.core.message.DataPacket;
import com.game.core.message.Request;
import com.game.core.message.RequestMessageByte;
import com.game.core.message.RequestMessageData;
import com.game.socket.logic.HandlerDispatcher;
import com.game.socket.logic.MessageQueue;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @Author: wx
 * @Date: 上午 10:37 2019/12/20 0020
 * @Desc: socket长连接
 * @version:
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class WebSocketFrameServerHandler  extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Autowired
    private HandlerDispatcher handlerDispatcher;

    private static AtomicLong id_gen = new AtomicLong(0);

    private static
    AttributeKey<Long> channel_session_id = AttributeKey
            .valueOf("channel_session_id");

    private ConcurrentHashMap<Long,Request> concurrentHashMap = new ConcurrentHashMap<>();


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("注册用户 channelRegistered:{}",ctx);
        //这里可以放入Redis哪个游戏的用户上线
        ctx.fireChannelRegistered();

        //绑定
        long sessionId = id_gen.incrementAndGet();
        ctx.channel().attr(channel_session_id).set(sessionId);

        Request request = Request.builder().channelHandlerContext(ctx).sessionId(sessionId).build();
        concurrentHashMap.put(sessionId,request);

        //可以添加事件 队列
//        EventParam<NettyTcpSession> sessionEventParam = new EventParam<>();
//        SessionRegisterEvent sessionRegisterEvent = new SessionRegisterEvent(l, l, sessionEventParam);
//        BlockingQueue<SingleEvent> queue = new ArrayBlockingQueue(10);
//        queue.put(sessionRegisterEvent);
    }
    /** 空闲次数 */
    private int idle_count =1;
    
    /**
     * @Author: @
     * @Desc: 客户端主动发送心跳
     *       * 超时处理
     *       * 如果5秒没有接受客户端的心跳，就触发;
     *       * 如果超过两次，则直接关闭;
     *
     * @Date: 下午 5:33 2019/12/24 0024
     * @Param
     */
     
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
//        if (obj instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) obj;
//            //如果读通道处于空闲状态，说明没有接收到心跳命令
//            if (IdleState.READER_IDLE.equals(event.state())) {
//                System.out.println("已经5秒没有接收到客户端的信息了");
//                if (idle_count > 2) {
//                    System.out.println("关闭这个不活跃的channel");
//                    ctx.channel().close();
//                }
//                idle_count++;
//            }
//        } else {
//            super.userEventTriggered(ctx, obj);
//        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        handleWebSocketFrame(ctx, msg);
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

        Long aLong = ctx.channel().attr(channel_session_id).get();
        Request request = concurrentHashMap.get(aLong);

        if (frame instanceof CloseWebSocketFrame) {
            WebSocketHttpServerHandler webSocketServerHandler = (WebSocketHttpServerHandler) ctx.pipeline().get("WebSocketHttpServerHandler");
            WebSocketServerHandshaker handShaker = webSocketServerHandler.getHandshaker();
            handShaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }

        if(frame instanceof PingWebSocketFrame) {
            ctx.write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if (frame instanceof TextWebSocketFrame) {
            log.info("客户端发过来的消息 TextWebSocketFrame:{}",frame.retain());
            String message = ((TextWebSocketFrame) frame).text();
            DataPacket dataPacket = null;
            try {
                log.info("用户拿取到的消息 message:{}",message);
                dataPacket = JSONObject.parseObject(message, DataPacket.class);
                handlerDispatcher.getMessageQueue().add(new RequestMessageData(request,dataPacket));
            } catch (Exception e) {
                ctx.writeAndFlush(new TextWebSocketFrame(message));
                e.printStackTrace();
            }
            return;
        }

        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
            ByteBuf buf = binaryWebSocketFrame.content();
            handlerDispatcher.getMessageQueue().add(new RequestMessageByte(request,buf));
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //删除内存用户
        Long aLong = ctx.channel().attr(channel_session_id).get();
        concurrentHashMap.remove(aLong);
    }
}
