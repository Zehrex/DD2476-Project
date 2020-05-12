6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/tcp/core/ProtocolBody.java
package com.github.taoroot.taoiot.netty.tcp.core;

import io.netty.buffer.ByteBuf;

/**
 * 基础包接口
 *
 * @author zhiyi
 */
public interface ProtocolBody {
    /**
     * 解码数据体
     */
    void decodeBody(ByteBuf buffer);

    /**
     * 编码数据体
     *
     * @return
     */
    ByteBuf encodeBody();

    /**
     * 获取类型
     *
     * @return
     */
    int getPackType();

    /**
     * 获取类型-十六进制字符串形式
     *
     * @return
     */
    String getPackTypeHexStr();

    /**
     * 获取类型说明
     *
     * @return
     */
    String getPackTypeDesc();

    /**
     * 获取数据-十六进制字符串
     *
     * @return
     */
    String getHexStr();
}
