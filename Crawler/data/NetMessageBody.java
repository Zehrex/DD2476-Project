1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/message/NetMessageBody.java
package com.game.core.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wx
 * @Date: 上午 9:51 2019/12/24 0024
 * @Desc:
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetMessageBody {

    private byte[] bytes;

}
