1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/util/ProtocolBytes.java
package com.game.core.util;

import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date: 上午 11:03 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Component
public class ProtocolBytes {

    private byte[] bytes;

    private int start;

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 11:04 2019/12/27 0027
     * @Param
     */
    public void AddString(String str) {
        int length = str.length();
        byte[] lenBytes = BitConverterUtil.intToBytes(length);
        byte[] strBytes = str.getBytes();
        byte[] combineBytes = Unpooled.copiedBuffer(lenBytes, strBytes).array();
        if (bytes == null) {
            bytes = combineBytes;
        } else {
            bytes = Unpooled.copiedBuffer(bytes, combineBytes).array();
        }
    }

    /**
     * @Author: @
     * @Desc: 从协议中获取字符串内容
     * @Date: 上午 11:04 2019/12/27 0027
     * @Param
     */
    public String GetString() {
        if (bytes == null) {
            return "";
        }
        if (bytes.length < start + 4) {
            return "";
        }

        // 获得协议中字符串的长度
        int strLen = BitConverterUtil.BytesToInt(bytes, start);
        if (bytes.length < start + 4 + strLen) {
            return "";
        }

        // 获得协议中具体的字符串的内容
        String str = new String(bytes, start + 4, strLen);

        // start指针前移
        this.start = start + 4 + strLen;

        return str;
    }
}
