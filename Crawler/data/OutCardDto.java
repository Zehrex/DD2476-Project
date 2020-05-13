1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/dto/OutCardDto.java
package com.game.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: @
 * @Desc: 牌型
 * @Date: 下午 4:47 2020/1/6 0006
 * @Version: 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutCardDto {

    public List<Integer> cardThree = new ArrayList<Integer>();

    public List<Integer> cardTwo = new ArrayList<Integer>();

    /**
     * 0:没牛； 1：牛一 ~10：牛牛；11：四花牛；12：五花牛；13：炸弹；14：五小牛
     */
    public int type = -1;

    /**
     * 倍率
     */
    public int multiple;

    public int[] sortNumArray = new int[5];
}
