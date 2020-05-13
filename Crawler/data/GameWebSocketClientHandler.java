1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_client/src/main/java/com/game/handler/GameWebSocketClientHandler.java
package com.game.handler;

import com.game.decoder.MessageDecoderFactory;
import com.game.encoder.MessageEncoderFactory;
import com.game.message.AbstractNetMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

/**
 * @Author: wx
 * @Date: 下午 8:30 2019/12/20 0020
 * @Desc:
 * @version:
 */
public class GameWebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

    private final WebSocketClientHandshaker handShaker;

    //todo 不太明白
    /**
     * 发消息同步获取结果
     */
    private ChannelPromise handshakeFuture;

    public GameWebSocketClientHandler(WebSocketClientHandshaker handShaker) {
        this.handShaker = handShaker;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //新的连接过来 握手
        handShaker.handshake(ctx.channel());
    }
    
    /**
     * @Author: @
     * @Desc: 连接断开
     * @Date: 下午 3:36 2019/12/21 0021
     * @Param
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("WebSocket Client disconnected!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        //握手是否完整
        if (!handShaker.isHandshakeComplete()) {
            //完成握手
            handShaker.finishHandshake(ch, (FullHttpResponse) msg);
            System.out.println("WebSocket Client connected!");
            //设置状态握手成功
            handshakeFuture.setSuccess();
            //发送消息
            /** 这里发送二进制的消息 或者其他类型的消息 */
            return;
        }

        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException(
                    "Unexpected FullHttpResponse (getStatus=" + response.status() +
                            ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        }

        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            System.out.println("WebSocket Client received message: " + textFrame.text());
        } else if (frame instanceof PongWebSocketFrame) {
            System.out.println("WebSocket Client received pong");
        } else if (frame instanceof CloseWebSocketFrame) {
            System.out.println("WebSocket Client received closing");
            ch.close();
            //二进制消息处理
        } else if(frame instanceof BinaryWebSocketFrame){
            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
            ByteBuf byteBuf = binaryWebSocketFrame.content();

            MessageDecoderFactory messageDecoderFactory = new MessageDecoderFactory();
            //解析消息
            AbstractNetMessage abstractNetMessage = messageDecoderFactory.praseMessage(byteBuf);

            Thread.sleep(1000L);
            sendTestMessage(ctx);
        }


    }
    /**
     * @Author: @
     * @Desc:  发送消息
     * @Date: 上午 10:53 2019/12/21 0021
     * @Param
     */
    private void sendTestMessage(ChannelHandlerContext ctx) {
        AbstractNetMessage abstractNetMessage = new AbstractNetMessage();
        MessageEncoderFactory messageEncoderFactory = new MessageEncoderFactory();
        ByteBuf byteBuf = messageEncoderFactory.createByteBuf(abstractNetMessage);

        BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(byteBuf);
        ctx.writeAndFlush(binaryWebSocketFrame);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }
        ctx.close();
    }
}
