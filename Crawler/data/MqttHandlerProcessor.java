6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/netty/mqtt/MqttHandlerProcessor.java
package com.github.taoroot.taoiot.netty.mqtt;

import cn.hutool.core.util.ClassUtil;
import io.netty.handler.codec.mqtt.MqttMessage;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : zhiyi
 * Date: 2020/3/3
 */
@Log4j2
public class MqttHandlerProcessor {

    private final static Map<String, MqttHandler<? extends MqttMessage>> CACHE_MAP = new ConcurrentHashMap<>();

    /**
     * 通过handler的泛型T，找出MqttMessage对应的所有handler
     */
    public static void scanHandler(String packageName) {
        Set<Class<?>> packages = ClassUtil.scanPackageBySuper(packageName, MqttHandler.class);
        for (Class<?> aPackage : packages) {
            try {
                registerHandler((MqttHandler<? extends MqttMessage>) aPackage.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过handler的泛型T，找出MqttMessage对应的所有handler
     */
    public static void registerHandler(MqttHandler<? extends MqttMessage> packageHandler) {
        // 从泛型接口中拿出类型
        ParameterizedType parameterizedType = (ParameterizedType) packageHandler.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
        if (!CACHE_MAP.containsKey(actualTypeArgument.getTypeName())) {
            CACHE_MAP.put(actualTypeArgument.getTypeName(), packageHandler);
            log.info("Register Handler: {} {}", actualTypeArgument.getTypeName(), packageHandler.getClass().getName());
        }
    }

    /**
     * 通过handler的泛型T，找出MqttMessage对应的所有handler
     */
    public static MqttHandler<? extends MqttMessage> getHandler(Class<? extends MqttMessage> classType) {
        return CACHE_MAP.get(classType.getName());
    }
}
