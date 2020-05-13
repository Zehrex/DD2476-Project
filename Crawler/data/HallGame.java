1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/dto/HallGame.java
package com.game.entity.dto;

import com.game.entity.GameJack;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wx
 * @Date: 上午 10:40 2019/12/27 0027
 * @Desc: 游戏大厅配置
 * @version:
 */
@Data
@Builder
public class HallGame {

    private Integer channel;

    private Integer gameId;

    private GameJack gameJack;

    private Map<Integer,Room> roomMap = new HashMap<>();

}
