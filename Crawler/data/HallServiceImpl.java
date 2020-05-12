1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/impl/HallServiceImpl.java
package com.game.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.game.coding.encoder.MessageEncoderFactoryApp;
import com.game.common.connst.Const;
import com.game.common.connst.MessageKey;
import com.game.core.dieline.Singleton;
import com.game.core.message.AbstractNetMessage;
import com.game.core.message.DataPacket;
import com.game.core.message.NetMessageBody;
import com.game.core.message.Request;
import com.game.core.util.GetBeanUtil;
import com.game.entity.SdkUser;
import com.game.entity.dto.HallGameMgr;
import com.game.entity.dto.Player;
import com.game.service.HallInitializer;
import com.game.service.HallService;
import com.game.service.game.SdkUserService;
import com.game.service.game.impl.SdkUserServiceImpl;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author: wx
 * @Date: 上午 11:07 2020/1/2 0002
 * @Desc:
 * @version:
 */
@Slf4j
@Service
public class HallServiceImpl extends abstractService implements HallService{

    @Autowired
    private SdkUserService sdkUserService;

    @Autowired
    private MessageEncoderFactoryApp messageEncoderFactoryApp;

    @PostConstruct
    public void init() {
        sdkUserService = GetBeanUtil.getBean(SdkUserService.class);
    }

    @Override
    public void invokeMethod(short cmd, Request request, DataPacket dataPacket) {
        super.invokeMethod(cmd,request,dataPacket);
    }

    @Override
    public void joinHall(Request request, JSONObject jsonObject) {
        String token = jsonObject.getString(Const.Constant.TOKEN);
        token = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1Nzg0MDQ0ODcsInN1YiI6IjEyMzQ1Njc4OTFfMTAwMyIsImlhdCI6MTU3ODM4Mjg4Nzg5OH0.rLHugb6ocTmwT9zS9fXG541xnkyGlAZrXThCsjC0UU3-0PutubXeTi9HAVpe8C_kYinA6Bfl9umP9jrZefOQdA";
        Integer gameId = HallInitializer.baseLocalMemory.getHallGameMgr().getHallGameMap().get(Const.Constant.CHANNEL).getGameId();
        SdkUser sdkUser = sdkUserService.getSdkUser(request,token,gameId,Const.Number.ONE);
        if (Objects.isNull(sdkUser)) {
            log.error("玩家不存在");
        }
        Player player = new Player(sdkUser.getSdkUserId());
        //逻辑处理
        HallInitializer.baseLocalMemory.getHallGameMgr().getPlayerList().add(player);

        byte[] bytes = JSONObject.toJSONBytes(player);
        //发送用户加入大厅协议
        AbstractNetMessage abstractNetMessage = new AbstractNetMessage(new NetMessageBody(bytes), MessageKey.SERVER_CONFIGS.getKey());
        ByteBuf byteBuf = messageEncoderFactoryApp.createByteBuf(abstractNetMessage);
        request.sendAndFlush(byteBuf);
    }
}
