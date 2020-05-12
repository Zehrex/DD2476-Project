1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/GameConfig.java
package com.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wx
 * @Date: 下午 2:17 2019/12/31 0031
 * @Desc: 游戏配置
 * @version:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameConfig {

    private Integer gameId;

    private Integer status;

    private Integer recharge;

    private Integer gameType;

    private Integer sort;

    private String name;

    private Integer isThird;

    private List<Room> room = new ArrayList<>();


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Room {

        private Integer id;

        private Integer gameNo;

        private String name;

        private Integer revenuePerc;

        private double baseScore;

        private double coinLimit;

        private double maxQiangMult;

        private double maxBetMult;

        private Integer winPerc;

        private Integer status;
    }

}
