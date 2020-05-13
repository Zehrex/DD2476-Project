1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/HallInitializer.java
package com.game.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.game.common.base.BaseLocalMemory;
import com.game.common.connst.Const;
import com.game.common.connst.RedisKey;
import com.game.core.runner.AppInitializer;
import com.game.core.util.RedisUtil;
import com.game.entity.GameConfig;
import com.game.entity.GameJack;
import com.game.entity.ServerInfoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: wx
 * @Date: 下午 5:48 2019/12/27 0027
 * @Desc: 初始化配置到内存
 * @version:
 */
@Slf4j
@Component
public class HallInitializer extends BaseLocalMemory implements AppInitializer {

    @Autowired
    private RedisUtil redisUtil;

    public static Integer GAME_ID = 40070002;

    @Override
    public void init() throws Exception {
        log.info("开始检测配置");
        this.detection();
    }

    @Override
    public int order() {
        return Const.Number.ONE;
    }

    @Override
    public void detection() {
        String channelIdStr = redisUtil.getStr(RedisKey.CHANNEL_IDS.getKey());
        JSONArray jsonArray = JSONArray.parseArray(channelIdStr);
        Map<Integer, GameConfig> map = baseLocalMemory.getGameConfigMap();
        Map<Integer, GameJack> gameJackMap = baseLocalMemory.getGameJackMap();

        jsonArray.forEach(channelId -> {
            log.info("渠道赋值游戏配置 channelId:{}", channelId);
            String gameConfigStr = redisUtil.getHash(RedisKey.GAME_CONFIGS.getKey() + channelId, GAME_ID.toString());
            map.put(Integer.valueOf(channelId.toString()), JSONObject.parseObject(gameConfigStr, GameConfig.class));
            baseLocalMemory.setGameConfigMap(map);

            String jackPotList = redisUtil.getHash(RedisKey.JACK_POT.getKey() + channelId, GAME_ID.toString());
            gameJackMap.put(Integer.valueOf(channelId.toString()), JSONObject.parseObject(jackPotList, GameJack.class));
            baseLocalMemory.setGameJackMap(gameJackMap);
        });


        String serverConfig = redisUtil.getHash(RedisKey.SERVER_CONFIGS.getKey(), GAME_ID.toString());
        ServerInfoConfig serverInfoNew = JSONObject.parseObject(serverConfig, ServerInfoConfig.class);

        if (Objects.nonNull(serverInfoNew)) {
            log.info("开始赋值启动项目所需要的配置信息 GameId:{} port:{}", serverInfoNew.getGameId(), serverInfoNew.getStartPort());
            baseLocalMemory.setServerInfo(serverInfoNew);
        }

    }
}
