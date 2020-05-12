1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/RegisterService.java
package com.game.service;

import com.game.common.base.BaseLocalMemory;
import com.game.common.connst.Const;
import com.game.core.annotation.MessageCommandAnnotation;
import com.game.core.runner.AppInitializer;
import com.game.core.util.SomeUtil;
import com.game.handler.impl.RegisterHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: wx
 * @Date: 下午 12:02 2020/1/2 0002
 * @Desc:  注册接口
 * @version:
 */
@Data
@Slf4j
@Component
public class RegisterService extends BaseLocalMemory implements AppInitializer {

    @Override
    public void init() throws Exception {
        this.detection();
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
                Method[] methods = aClassNew.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(MessageCommandAnnotation.class)) {
                        MessageCommandAnnotation messageCommandAnnotation = method.getAnnotation(MessageCommandAnnotation.class);
                        if (messageCommandAnnotation != null) {
                            baseLocalMemory.getMethodLocalMap().put(messageCommandAnnotation.cmd(), method);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int order() {
        return Const.Number.TWO;
    }
}
