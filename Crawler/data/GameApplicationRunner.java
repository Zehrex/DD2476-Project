1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/socket/netty/GameApplicationRunner.java
package com.game.socket.netty;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wx
 * @Date: 下午 5:46 2019/12/21 0021
 * @Desc:
 * @version:
 */
@Component
public class GameApplicationRunner implements ApplicationRunner, DisposableBean {

    private final ApplicationContext applicationContext;

    private List<GameInitializer> initializers;

    @Autowired
    public GameApplicationRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        for (GameInitializer txLcnInitializer : initializers) {
            txLcnInitializer.destroy();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, GameInitializer> runnerMap = applicationContext.getBeansOfType(GameInitializer.class);
        initializers = runnerMap.values().stream().sorted(Comparator.comparing(GameInitializer::order))
                .collect(Collectors.toList());

        for (GameInitializer txLcnInitializer : initializers) {
            //运行netty的服务器
            txLcnInitializer.init();
        }
    }
}
