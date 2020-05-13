1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/message/DataPacket.java
package com.game.core.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wx
 * @Date: 上午 11:17 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPacket {

    private int cmd;

    private String data;
}
