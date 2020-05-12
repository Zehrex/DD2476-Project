1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/GameJack.java
package com.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wx
 * @Date: 下午 5:09 2019/12/27 0027
 * @Desc: 奖池的实体类
 * @version:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameJack {

    private double boundary;

    private Integer channelId;

    private double extSpend;

    private double extSpendPerc;

    private Integer gameId;

    private Integer id;

    private double jackpot;

    private Integer[] steal = new Integer[2];
}
