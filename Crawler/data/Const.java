1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/common/connst/Const.java
package com.game.common.connst;


/**
 * @Author: wx
 * @Date: 上午 10:47 2019/12/27 0027
 * @Desc:
 * @version:
 */
public interface Const {

    interface CmdNum {

        /**加入大厅*/
        public Integer COINT_HALL = 1001;
        /**加入房间*/
        public Integer COINT_ROOM = 2001;
    }

    interface Constant {

        public String TOKEN = "token";

        public Integer CHANNEL = 1003;
    }

    interface Number {
        Integer FU_ONE = -1;
        Integer ZERO = 0;
        Integer ONE = 1;
        Integer TWO = 2;
        Integer THREE = 3;
        Integer FOUR = 4;
        Integer FRIVE = 5;
        int ONE_QIAN = 1000;
    }

}
