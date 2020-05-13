1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/ServerInfoConfig.java
package com.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wx
 * @Date: 下午 1:58 2019/12/31 0031
 * @Desc:
 * @version:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfoConfig {

    private Integer id;

    private String addr;

    private Integer state;

    private Integer gameId;

    private String note;

    private Integer gameNum;

    private Integer gameType;

    private Integer gameGenres;

    private String insideAddr;

    private Integer startPort;

    private Integer isThird;

}
