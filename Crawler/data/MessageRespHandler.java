23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/internal/handler/MessageRespHandler.java
package com.takiku.im_lib.internal.handler;

public interface MessageRespHandler<response extends com.google.protobuf.GeneratedMessageV3> {
    /**
     * 是否是服务端响应消息
     * @param msg
     * @return
     */
    boolean isResponse(Object msg); //是否是响应消息

    /**
     * 服务端响应的tag对应发送哪条消息
     * @param pack
     * @return
     */
    String tagResponse(response pack);//标记这属于那个request的响应

}
