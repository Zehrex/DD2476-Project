1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/message/NetMessageHead.java
package com.game.core.message;

import lombok.Data;

/**
 * @Author: wx
 * @Date: 上午 9:50 2019/12/24 0024
 * @Desc:
 * @version:
 */
@Data
public class NetMessageHead {

    public static final short MESSAGE_HEADER_FLAG = 0x2425;

    /**
     * 魔法头
     */
    private short head;
    /**
     * 版本号
     */
    private byte version;
    /**
     * 长度
     */
    private int length;
    /**
     * 命令
     */
    private Integer cmd;
    /**
     * 序列号
     */
    private int serial;

    public NetMessageHead(){
        this.head = MESSAGE_HEADER_FLAG;
    }
}
