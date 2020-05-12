1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/dieline/Singleton.java
package com.game.core.dieline;

import com.game.core.message.NetMessageHead;
import com.game.mapper.SdkUserRepository;
import com.game.service.HallService;
import com.game.service.game.SdkUserService;
import com.game.service.game.impl.SdkUserServiceImpl;
import com.game.service.impl.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: @
 * @Desc: 单列 懒汉式
 * @Date: 上午 11:42 2020/1/7 0007
 * @Version: 0.1
 */
@Component
public class Singleton {

    private static volatile HallService hallService = null;

    private static volatile SdkUserService sdkUserService = null;

    private static volatile NetMessageHead netMessageHead = null;

    private Singleton() {
    }

    public static HallService getHallService() {
        if (hallService == null) {
            synchronized (HallService.class) {
                if (hallService == null) {
                    hallService = new HallServiceImpl();
                }
            }
        }
        return hallService;
    }

    public static SdkUserService getSdkUserService() {
        if (sdkUserService == null) {
            synchronized (HallService.class) {
                if (sdkUserService == null) {
                    sdkUserService = new SdkUserServiceImpl();
                }
            }
        }
        return sdkUserService;
    }

    public static NetMessageHead getNetMessageHead() {
        if (netMessageHead == null) {
            synchronized (NetMessageHead.class) {
                if (netMessageHead == null) {
                    netMessageHead = new NetMessageHead();
                }
            }
        }
        return netMessageHead;
    }

}
