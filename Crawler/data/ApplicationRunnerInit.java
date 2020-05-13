1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/runner/ApplicationRunnerInit.java
package com.game.core.runner;

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
 * @Date: 上午 11:48 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Component
public class ApplicationRunnerInit implements ApplicationRunner, DisposableBean {
    private final ApplicationContext applicationContext;

    private List<AppInitializer> initializers;

    @Autowired
    public ApplicationRunnerInit(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        for (AppInitializer txLcnInitializer : initializers) {
            txLcnInitializer.destroy();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, AppInitializer> runnerMap = applicationContext.getBeansOfType(AppInitializer.class);
        //好处 在于可以根据 order进行排序 执行循序很好的控制
        initializers = runnerMap.values().stream().sorted(Comparator.comparing(AppInitializer::order))
                .collect(Collectors.toList());
        for (AppInitializer txLcnInitializer : initializers) {
            txLcnInitializer.init();
        }
    }
}
