1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/server/impl/NettyServerImpl.java
package com.game.socket.server.impl;

import com.game.entity.ServerInfoConfig;
import com.game.socket.factory.ThreadNameFactory;
import com.game.socket.netty.GameLoggerHandler;
import com.game.socket.netty.WebSocketFrameServerHandler;
import com.game.socket.netty.WebSocketHttpServerHandler;
import com.game.socket.netty.initializer.ServerInitializer;
import com.game.socket.server.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date: 下午 5:53 2019/12/21 0021
 * @Desc: 启动服务器
 * @version:
 */
@Slf4j
@Component
public class NettyServerImpl implements NettyServer {

    static final boolean SSL = System.getProperty("ssl") != null;

    private EventLoopGroup workerGroup;
    private NioEventLoopGroup bossGroup;

    public static final String NET_WEB_SOCKET_BOSS = "net_web_socket_boss";
    public static final String NET_WEB_SOCKET_WORKER = "net_web_socket_worker";
    private static final String WEBSOCKET_PATH = "/websocket";

    @Autowired
    private WebSocketFrameServerHandler webSocketFrameServerHandler;

    @Autowired
    private WebSocketHttpServerHandler webSocketHttpServerHandler;

    @Override
    public void startNetty(ServerInfoConfig serverInfoConfig) throws CertificateException, SSLException {
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
        bossGroup = new NioEventLoopGroup(1, new ThreadNameFactory(NET_WEB_SOCKET_BOSS));
        workerGroup = new NioEventLoopGroup(0, new ThreadNameFactory(NET_WEB_SOCKET_WORKER));

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            if (sslCtx != null) {
                                ch.pipeline().addLast(sslCtx.newHandler(ch.alloc()));
                            }
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(65536));
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new IdleStateHandler(1000,
                                    1000, 1000, TimeUnit.MILLISECONDS));
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
                            ch.pipeline().addLast("logger", new GameLoggerHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(new WebSocketServerCompressionHandler());
                            ch.pipeline().addLast(new DefaultEventExecutorGroup(300), "WebSocketHttpServerHandler", webSocketHttpServerHandler);
                            ch.pipeline().addLast(new DefaultEventExecutorGroup(300), "WebSocketFrameServerHandler", webSocketFrameServerHandler);
                        }
                    });
//            serverInfoConfig.setStartPort(8090);
            Channel ch = serverBootstrap.bind(serverInfoConfig.getStartPort()).sync().channel();

            System.out.println("Open your web browser and navigate to " +
                    (SSL ? "https" : "http") + "://127.0.0.1:" + serverInfoConfig.getStartPort() + '/');

            /*主线程到这里就wait 子线程退出了，子线程才是真正监听和接受请求的*/
            ch.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("服务器启动成功！");
                }
            }).sync();
        } catch (Exception e) {
            log.error("启动服务器失败!");
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
