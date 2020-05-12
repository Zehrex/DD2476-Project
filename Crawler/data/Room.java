1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/dto/Room.java
package com.game.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: wx
 * @Date: 上午 10:41 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Integer id;

    private Integer gameNo;

    private String name;

    /**底分*/
    private double baseScore;

    /**准入分数*/
    private double coinLimit;

    /** 赢家抽水比列*/
    private Integer revenuePerc;

    /**状态*/
    private Integer state;

    /**获胜几率*/
    private Integer winPerc;

    /**最大人数*/
    private Integer maxRole;

    /**当前人数*/
    private Integer currentRole;

    /**桌子*/
    private Map<Integer,Table> tableMap;

}
