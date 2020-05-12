1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/initializer/NettyServerInitializer.java
package com.game.socket.netty.initializer;

import com.game.common.base.BaseLocalMemory;
import com.game.common.connst.Const;
import com.game.core.runner.AppInitializer;
import com.game.entity.GameConfig;
import com.game.entity.dto.HallGame;
import com.game.entity.dto.HallGameMgr;
import com.game.entity.dto.Room;
import com.game.service.HallInitializer;
import com.game.socket.logic.ScheduledThread;
import com.game.socket.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: wx
 * @Date: 下午 5:55 2019/12/21 0021
 * @Desc: 初始化大厅和房间配置
 * @version:
 */
@Slf4j
@Component
public class NettyServerInitializer extends BaseLocalMemory implements AppInitializer {

    private final NettyServer nettyServer;

    @Autowired
    public NettyServerInitializer(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Autowired
    private ScheduledThread scheduledThread;

    @Override
    public void init() throws Exception {
        log.info("用户的信息");
        //初始化游戏的基础配置
        this.detection();
        //开启定时线程
        scheduledThread.start();
        //启动服务器
        nettyServer.startNetty(HallInitializer.baseLocalMemory.getServerInfo());
    }

    @Override
    public int order() {
        return Const.Number.FOUR;
    }

    @Override
    public void detection() {
        //初始化游戏信息
        Map<Integer, GameConfig> map = HallInitializer.baseLocalMemory.getGameConfigMap();
        Map<Integer, HallGame> hallGameMap = new HashMap<>(16);

        for (Map.Entry<Integer, GameConfig> integerGameConfigEntry : map.entrySet()) {
            Integer channel = integerGameConfigEntry.getKey();
            GameConfig gameConfig = integerGameConfigEntry.getValue();
            if(Objects.nonNull(gameConfig)) {
                List<GameConfig.Room> roomList = gameConfig.getRoom();
                if(!CollectionUtils.isEmpty(roomList) || roomList.size()>Const.Number.ZERO) {
                    Map<Integer, Room> roomMap = roomList.stream().collect(Collectors.toMap(GameConfig.Room::getGameNo, dto -> Room.builder().baseScore(dto.getBaseScore())
                            .coinLimit(dto.getCoinLimit())
                            .gameNo(dto.getGameNo())
                            .id(dto.getId())
                            .maxRole(Const.Number.ONE_QIAN)
                            .currentRole(Const.Number.ZERO)
                            .name(dto.getName())
                            .revenuePerc(dto.getRevenuePerc())
                            .state(dto.getStatus())
                            .tableMap(new HashMap<>(16))
                            .winPerc(dto.getWinPerc()).build()));

                    HallGame hallGame = HallGame.builder().channel(channel)
                            .gameId(gameConfig.getGameId())
                            .gameJack(HallInitializer.baseLocalMemory.getGameJackMap().get(channel))
                            .roomMap(roomMap).build();

                    hallGameMap.put(channel,hallGame);
                }
            }
        }
        HallInitializer.baseLocalMemory.setHallGameMgr(HallGameMgr.builder().hallGameMap(hallGameMap).playerList(new ArrayList<>()).build());
    }
}
