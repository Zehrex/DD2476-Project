1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/GameLoggerHandler.java
package com.game.socket.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wx
 * @Date: 上午 10:09 2019/12/20 0020
 * @Desc:
 * @version:
 */
@Slf4j
public class GameLoggerHandler extends LoggingHandler {

    public GameLoggerHandler(LogLevel level) {
        super(level);
    }

    private final ChannelFutureListener exceptionListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if(!future.isSuccess()){
                Throwable throwable = future.cause();
                if(throwable != null) {

                }
            }
        }
    };

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ChannelPromise channelPromise = promise.unvoid();
        channelPromise.addListener(exceptionListener);
        ctx.write(msg, promise);
    }
}
