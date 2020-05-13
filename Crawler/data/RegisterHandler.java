1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/handler/impl/RegisterHandler.java
package com.game.handler.impl;

import com.game.common.base.BaseLocalMemory;
import com.game.common.connst.Const;
import com.game.core.annotation.MessageCommandAnnotation;
import com.game.core.runner.AppInitializer;
import com.game.core.util.SomeUtil;
import com.game.handler.Handler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: wx
 * @Date: 上午 10:06 2019/12/27 0027
 * @Desc: 初始化游戏配置
 * @version:
 */
@Data
@Slf4j
@Component
public class RegisterHandler extends BaseLocalMemory implements AppInitializer {

    @Override
    public void init() throws Exception {
        this.detection();
    }

    @Override
    public int order() {
        return Const.Number.THREE;
    }

    @Override
    public void destroy() throws Exception {
        log.info("销毁配置信息!");
    }

    @Override
    public void detection() {
        String path = RegisterHandler.class.getPackage().getName();
        log.info("获取当前包的地址 path:{}",path);
        List<Class<?>> classFromPackage = SomeUtil.getClasssFromPackage(path);
        for (Class<?> aClass : classFromPackage) {
            String name = aClass.getName();
            log.info("类的名字 name:{}",name);
            try {
                Class<?> aClassNew = Class.forName(name);
                MessageCommandAnnotation aClassNewAnnotation = aClassNew.getAnnotation(MessageCommandAnnotation.class);
                if(Objects.nonNull(aClassNewAnnotation)) {
                    Integer cmd = aClassNewAnnotation.cmd();
                    Handler handler = (Handler) aClassNew.getDeclaredConstructor().newInstance();
                    handler.operation();
                    Map<Integer, Handler> handlerLocalMap = baseLocalMemory.getHandlerLocalMap();
                    handlerLocalMap.put(cmd,handler);
                    baseLocalMemory.setHandlerLocalMap(handlerLocalMap);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
