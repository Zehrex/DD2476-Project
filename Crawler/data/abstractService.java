1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/service/impl/abstractService.java
package com.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.common.base.BaseService;
import com.game.core.message.DataPacket;
import com.game.core.message.Request;
import com.game.service.RegisterService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: wx
 * @Date: 下午 3:08 2020/1/2 0002
 * @Desc:
 * @version:
 */
public abstract class abstractService implements BaseService {

    @Override
    public void invokeMethod(short cmd, Request request, DataPacket dataPacket) {
        Map<Integer, Method> methodLocalMap = RegisterService.baseLocalMemory.getMethodLocalMap();
        Method method = methodLocalMap.get(cmd);
        try {
            JSONObject jsonObject = JSON.parseObject(dataPacket.getData());
            method.invoke(this.getClass(),request,jsonObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
