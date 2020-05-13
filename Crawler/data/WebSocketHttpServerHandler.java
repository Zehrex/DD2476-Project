1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/WebSocketHttpServerHandler.java
package com.game.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author: wx
 * @Date: 上午 10:34 2019/12/20 0020
 * @Desc: http请求
 * @version:
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHttpServerHandler extends SimpleChannelInboundHandler<HttpRequest> {

    private static final String WEBSOCKET_PATH = "/websocket";

    private WebSocketServerHandshaker handshaker;

    public WebSocketServerHandshaker getHandshaker() {
        return handshaker;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        if (msg instanceof HttpRequest) {
            handleHttpRequest(ctx,msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //消息接受完毕刷新ctx
        ctx.flush();
    }

    public void handleHttpRequest(ChannelHandlerContext ctx, HttpRequest req) {
        //编码失败
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }

        if (req.method() != GET) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
            return;
        }

        if ("/favicon.ico".equals(req.uri())) {
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND);
            sendHttpResponse(ctx, req, res);
            return;
        }

        if("/".equals(req.uri()) || "/index.html".equals(req.uri())) {
            String location = getWebSocketLocation(ctx.pipeline(), req);
            ByteBuf content = WebSocketServerIndexPage.getContent(location);
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
            res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            HttpUtil.setContentLength(res, content.readableBytes());

            sendHttpResponse(ctx, req, res);
            return;
        } else {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND));
        }

        //todo netty是如何建立握手
        /*
         * 1、客户端进入sync队列 ->boosGroup
         * 2、服务端返回给客户端ack
         * 3、从sync队列移入到accept队列 ->workGroup
         * */
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketLocation(ctx.pipeline(),req), null, true, 5 * 1024 * 1024);
        //获取WS握手对象
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }

        //获取游戏信息 可以将用户的信息放入到redis

    }

    private String getWebSocketLocation(ChannelPipeline cp,HttpRequest req) {
        String location =  req.headers().get(HttpHeaderNames.HOST) + WEBSOCKET_PATH;
        if (cp.get(SslHandler.class)!=null) {
            return "wss://" + location;
        } else {
            return "ws://" + location;
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req, FullHttpResponse res) {
        if(res.status().code() != 200) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(byteBuf);
            byteBuf.release();
            HttpUtil.setContentLength(res,res.content().readableBytes());
        }
        //ChannelFuture保存channel异步操作的结果
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if(!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            //异步监听回调
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
