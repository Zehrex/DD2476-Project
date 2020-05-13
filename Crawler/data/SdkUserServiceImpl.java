1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/game/impl/SdkUserServiceImpl.java
package com.game.service.game.impl;

import com.game.common.connst.Const;
import com.game.core.message.Request;
import com.game.core.util.GetBeanUtil;
import com.game.core.util.JwtTokenUtils;
import com.game.core.util.RedisUtil;
import com.game.entity.SdkUser;
import com.game.mapper.SdkUserRepository;
import com.game.service.HallService;
import com.game.service.game.SdkUserService;
import com.game.socket.netty.WebSocketManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @Author: @
 * @Desc:
 * @Date: 下午 5:50 2020/1/4 0004
 * @Version: 0.1
 */
@Slf4j
@Service
public class SdkUserServiceImpl implements SdkUserService {

    private SdkUserRepository sdkUserRepository;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        sdkUserRepository = GetBeanUtil.getBean(SdkUserRepository.class);
        redisUtil = GetBeanUtil.getBean(RedisUtil.class);
    }


    @Override
    public SdkUser getSdkUser(Request request, String token, Integer gameId, Integer gameNum) {
        SdkUser sdkUser = null;
        boolean flag = false;
        if (!StringUtils.isEmpty(token)) {
            String usernameFromToken = jwtTokenUtils.getUsernameFromToken(token);
            log.info("token获取的用户信息 usernameFromToken:{}", usernameFromToken);
            if (Objects.nonNull(usernameFromToken)) {
                String s = redisUtil.getHash(Const.Constant.TOKEN, usernameFromToken);
                log.info("获取的token s =:{}", s);
                if (!StringUtils.isEmpty(s)) {
                    boolean equals = s.equals(token);
                    if (equals) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                String[] split = usernameFromToken.split("_");
                sdkUser = sdkUserRepository.findBySdkUserOpenidAndSdkUserChannelid(split[Const.Number.ZERO],Integer.valueOf(split[Const.Number.ONE]));
                //todo 怎么样获取用户id
                String userId = "";
                Request ws = WebSocketManager.getWebSocket(Integer.valueOf(sdkUser.getSdkUserId()));
                if (Objects.nonNull(ws)) {
                    ws.close();
                    log.info("玩家异地登陆 userId:{}", ws.getUserId());
                }
                //关闭之前的 创建新的连接
            }
            WebSocketManager.bindWebSocket(sdkUser.getSdkUserId(), request, gameId, gameNum);
            request.setChannel(sdkUser.getSdkUserChannelid());
            //放置用户24小时过期

        }
        return sdkUser;
    }

}
