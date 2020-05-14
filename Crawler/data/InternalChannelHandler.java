23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/internal/handler/InternalChannelHandler.java
package com.takiku.im_lib.internal.handler;

import io.netty.channel.ChannelHandlerContext;

public interface InternalChannelHandler {
    void channelRead(ChannelHandlerContext ctx, Object msg);
}
