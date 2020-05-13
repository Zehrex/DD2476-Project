1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/initializer/ServerInitializer.java
package com.game.socket.netty.initializer;

import com.game.entity.ServerInfoConfig;
import com.game.socket.netty.GameLoggerHandler;
import com.game.socket.netty.WebSocketFrameServerHandler;
import com.game.socket.netty.WebSocketHttpServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date: 上午 11:53 2019/12/27 0027
 * @Desc:
 * @version:
 */
public class ServerInitializer extends ChannelInitializer<Channel> {

    private static final String WEBSOCKET_PATH = "/websocket";

    private ServerInfoConfig serverMessage;

    private SslContext sslCtx;

    public ServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Autowired
    private WebSocketFrameServerHandler webSocketFrameServerHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
//        if (sslCtx != null) {
//            ch.pipeline().addLast(sslCtx.newHandler(ch.alloc()));
//        }
///*        ch.pipeline().addLast(new LengthFieldPrepender(4, false));
//        ch.pipeline().addLast("encoder", new HttpResponseEncoder());
//        ch.pipeline().addLast("decoder", new HttpRequestDecoder());*/
//        ch.pipeline().addLast(new HttpServerCodec());
//        ch.pipeline().addLast(new HttpObjectAggregator(65536));
//        ch.pipeline().addLast(new ChunkedWriteHandler());
//        ch.pipeline().addLast(new IdleStateHandler(1000,
//                1000, 1000, TimeUnit.MILLISECONDS));
//        ch.pipeline().addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
//        ch.pipeline().addLast("logger", new GameLoggerHandler(LogLevel.DEBUG));
//        ch.pipeline().addLast(new WebSocketServerCompressionHandler());
//        ch.pipeline().addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
//        ch.pipeline().addLast(new DefaultEventExecutorGroup(300), "WebSocketHttpServerHandler", new WebSocketHttpServerHandler());
//        ch.pipeline().addLast(new DefaultEventExecutorGroup(300), "WebSocketFrameServerHandler", webSocketFrameServerHandler);

    }
}
