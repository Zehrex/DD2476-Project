1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/base/BaseRole.java
package com.game.entity.base;

import com.game.common.connst.Const;
import com.game.entity.dto.OutCardDto;
import com.game.entity.dto.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: wx
 * @Date: 上午 10:40 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Data
@NoArgsConstructor
public class BaseRole {

    /** 上线状态 0：没掉线；1：掉线了 */
    private int onLine = Const.Number.ZERO;

    /** 是否是托管*/
    private boolean isAuto;

    private Integer userId;

    private String userName;

    private String headImg;

    private Integer sex;

    /**玩家金币*/
    private BigDecimal coin;

    /**用户的位置*/
    private Integer position = Const.Number.FU_ONE;

    /** 玩家当前状态 */
    private Integer state;

    private String ip;

    private String address;

    private Integer channel;

    private boolean hasAccount;

    /**当局分数*/
    private BigDecimal roundScore = BigDecimal.ZERO;


    private Integer lastPosition = Position.NONE; //上次位置

    /**
     * 玩家手牌(给客户端的)
     */
    private Integer[] remainCards = new Integer[0];
    /**
     * 真实手牌
     */
    private Integer[] realCards = new Integer[0];
    /**
     * 给客户端的
     */
    private OutCardDto bestOutCardDto;
    /**
     * 真实地
     */
    private OutCardDto realOutCardDto;
    /**
     * 真实地
     */
    public boolean hasShowCard = false;
    /**
     * 下注倍数
     */
    private Integer betCoin;
    /**
     * 抢庄倍数
     */
    private Integer quaCoin;

    private Integer tongShaNum;

    private Integer tongPeiNum;

    private Integer winNum;

    private Integer loseNum;

    private Integer niuNiuNum;

    /**允许的抢庄*/
    public Integer[] allowQua = new Integer[0];
    /**允许的下注*/
    public Integer[] allowBet = new Integer[0];

    /**牌型*/
    public Integer realPattern;
}
