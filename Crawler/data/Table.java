1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/dto/Table.java
package com.game.entity.dto;

import com.game.entity.config.TableConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: wx
 * @Date: 上午 10:41 2019/12/27 0027
 * @Desc: 桌子
 * @version:
 */
public class Table {

    private TableConfig tableConfig;

    private String seq;

    private Date createTime;

    private Date endTime;

    private Integer tableState;

    private Integer owner;

    private Integer[] cards = new Integer[0];

    /**
     * 庄家位置
     */
    private Integer zhuangPos = Position.EAST;
    /**
     * 当前赢家位置
     */
    private Integer winnerPos;
    /**
     * 当前牛牛位置
     */
    private Integer nnPos;

    private Integer judge;

    private Integer robotNum;

    /**中彩玩家*/
    private List<Player> playerList = new ArrayList<>();

}
