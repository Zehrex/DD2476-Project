1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/handler/impl/CoinHallHandler.java
package com.game.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.game.core.annotation.MessageCommandAnnotation;
import com.game.core.dieline.Singleton;
import com.game.core.message.DataPacket;
import com.game.core.message.RequestMessageData;
import com.game.handler.Handler;
import com.game.service.HallService;
import com.game.service.impl.HallServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: wx
 * @Date: 上午 10:31 2019/12/27 0027
 * @Desc: 进入大厅
 * @version:
 */
@Slf4j
@Component
@MessageCommandAnnotation(cmd = 1002)
public class CoinHallHandler extends Handler {

    @Override
    public void operation() {
        this.str = "CoinHallHandler:{进入大厅}";
    }

    @Autowired
    private HallService hallService;

    private static HallService hallServiceNew;

    @PostConstruct
    public void init() {
        hallServiceNew = this.hallService;
    }


    @Override
    public void handlerMessage(RequestMessageData requestMessageData) {
        super.handlerMessage(requestMessageData);
       log.info("加入大厅");
        DataPacket dataPacket = requestMessageData.getDataPacket();
        hallServiceNew.joinHall(requestMessageData.getRequest(),JSONObject.parseObject(JSONObject.toJSONString(dataPacket)));
    }
}
