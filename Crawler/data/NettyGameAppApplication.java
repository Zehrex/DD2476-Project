1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/NettyGameAppApplication.java
package com.game;

import com.game.core.util.JwtTokenUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: wx
 * @Date: 上午 11:38 2019/12/27 0027
 * @Desc:
 * @version:
 */
@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(value = {JwtTokenUtils.class})
public class NettyGameAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyGameAppApplication.class, args);
    }
}