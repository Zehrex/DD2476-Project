23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/dispatcher/Connection.java
package com.takiku.im_lib.dispatcher;

import java.net.InetSocketAddress;

import io.netty.channel.Channel;


public interface Connection {

    Channel channel();
    InetSocketAddress InetSocketAddress();
}
