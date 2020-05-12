1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/handler/Handler.java
package com.game.handler;

import com.game.common.connst.Const;
import com.game.core.message.DataPacket;
import com.game.core.message.RequestMessageData;
import com.game.core.message.Request;
import com.game.service.HallService;
import com.game.service.RoomService;
import com.game.service.impl.HallServiceImpl;
import com.game.service.impl.RoomServiceImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: wx
 * @Date: 上午 10:23 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Slf4j
@NoArgsConstructor
public class Handler implements HandlerService{

    protected String str;

    private static final String methodName = "invokeMethod";

    @Autowired
    private HallService hallService;

    @Autowired
    private RoomService roomService;

    @Override
    public void operation() {
       log.info("交互消息:str{}",str);
    }
    
    /**
     * @Author: @
     * @Desc: 使用反射将可读性降低
     * @Date: 上午 11:39 2020/1/2 0002
     * @Param
     */
    @Override
    public void handlerMessage(Request request, DataPacket dataPacket) {
        this.operation();
        Integer cmd = dataPacket.getCmd();
        //todo 这里可以使用正则表达式 细节匹配
        /*要不要在这里 直接将消息做解析传入*/
        try {
            if(String.valueOf(cmd).length() == Const.Number.FOUR) {
                HallServiceImpl.class.getMethod(methodName,Short.class,Request.class,DataPacket.class).invoke(hallService,cmd,request,dataPacket);
            }else if(String.valueOf(cmd).length() == Const.Number.FRIVE){
                RoomServiceImpl.class.getMethod(methodName,Short.class,Request.class,DataPacket.class).invoke(roomService,cmd,request,dataPacket);
            }else {
                log.info("协议号不匹配 cmd:{}",cmd);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerMessage(RequestMessageData requestMessageData) {
    }

}
