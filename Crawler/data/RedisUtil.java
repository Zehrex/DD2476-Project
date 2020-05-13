1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/util/RedisUtil.java
package com.game.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date: 下午 5:52 2019/12/27 0027
 * @Desc:
 * @version:
 */
@Slf4j
@Component
public class RedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Author: @
     * @Desc:
     * @Date: 下午 2:08 2019/11/26 0026
     * @Param 获取字符串
     */
    public String getStr(String obj) {
        String s = stringRedisTemplate.opsForValue().get(obj);
        return s;
    }

    /**
     * @Author: @
     * @Desc: 批量获取
     * @Date: 下午 3:57 2019/11/26 0026
     * @Param
     */
    public List<String> getStrBatch(List<String> list) {
        List<String> stringList = stringRedisTemplate.opsForValue().multiGet(list);
        return stringList;
    }

    /**
     * @Author: @
     * @Desc: 插入一个 如果存在 返回false
     * @Date: 下午 3:54 2019/11/26 0026
     * @Param
     */
    public boolean setIfStr(String key, String value) {
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        return aBoolean;
    }

    /**
     * @Author: @
     * @Desc: 字符串赋值
     * @Date: 下午 3:47 2019/11/26 0026
     * @Param
     */
    public void setStr(String key, String value, Long time, TimeUnit timeUnit) {
        if (Objects.isNull(time) || Objects.isNull(timeUnit)) {
            stringRedisTemplate.opsForValue().set(key, value);
        }
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * @Author: @
     * @Desc: 字符串的批量插入
     * @Date: 下午 3:49 2019/11/26 0026
     * @Param
     */
    public void setStrMap(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * @Author: @
     * @Desc: 批量插入  没有重复的全部插入 有重复的全部不插入 返回false
     * @Date: 下午 3:53 2019/11/26 0026
     * @Param
     */
    public Boolean setStrIfMap(Map<String, String> map) {
        Boolean aBoolean = stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
        return aBoolean;
    }

    /**
     * @Author: @
     * @Desc: 获取指定key的值进行减1，如果value不是integer类型，会抛异常，如果key不存在会创建一个，默认value为0
     * @Date: 下午 4:01 2019/11/26 0026
     * @Param
     */
    public Long strDecrement(String key) {
        Long decrement = stringRedisTemplate.opsForValue().decrement(key);
        return decrement;
    }

    /**
     * @Author: @
     * @Desc: 获取指定key的值进行加1，如果value不是integer类型，会抛异常，如果key不存在会创建一个，默认value为0
     * @Date: 下午 4:02 2019/11/26 0026
     * @Param
     */
    public Long strIncrement(String key) {
        Long decrement = stringRedisTemplate.opsForValue().increment(key);
        return decrement;
    }

    /**
     * @Author: @
     * @Desc: 删除
     * @Date: 下午 4:03 2019/11/26 0026
     * @Param
     */
    public boolean strDelete(String key) {
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete(key);
        return delete;
    }


    /**
     * @Author: @
     * @Desc:
     * @Date: 下午 2:12 2019/11/26 0026
     * @Param map的获取
     */
    public String getHash(String key, String hashKey) {
        Object o = stringRedisTemplate.opsForHash().get(key, hashKey);
        return String.valueOf(o);
    }

    /**
     * @Author: @
     * @Desc:
     * @Date: 下午 2:12 2019/11/26 0026
     * @Param map的获取
     */
    public String getHashValue(String key) {
        Object o = stringRedisTemplate.opsForHash().values(key);
        return String.valueOf(o);
    }

    /**
     * @Author: @
     * @Desc: map的插入
     * @Date: 下午 4:06 2019/11/26 0026
     * @Param
     */
    public void putMap(String key, String mapKey, String value) {
        stringRedisTemplate.opsForHash().put(key, mapKey, value);
    }

    /**
     * @Author: @
     * @Desc: 集合的方式添加
     * @Date: 下午 4:08 2019/11/26 0026
     * @Param
     */
    public void putMapAll(String key, Map<String, String> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * @Author: @
     * @Desc:
     * @Date: 下午 4:00 2019/12/6 0006
     * @Param
     */
    public void deleteSession(String key, Integer userId, String sessionId) {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        for (Map.Entry<Object, Object> objectObjectEntry : entries.entrySet()) {
            if (String.valueOf(objectObjectEntry.getValue()).equals(String.valueOf(userId))) {
                if (!objectObjectEntry.getKey().equals(sessionId) ) {
                    log.info("失误操作，删除的key getKey:{} userId{}", objectObjectEntry.getKey(), userId);
                    this.deleteMap("sid", String.valueOf(objectObjectEntry.getKey()));
                }
            }
        }
    }

    /**
     * @Author: @
     * @Desc: 删除
     * @Date: 下午 4:10 2019/11/26 0026
     * @Param
     */
    public Long deleteMap(String key, String keyMap) {
        if (Objects.isNull(keyMap)) {
            Long delete = stringRedisTemplate.opsForHash().delete(key);
            return delete;
        } else {
            Long delete = stringRedisTemplate.opsForHash().delete(key, keyMap);
            return delete;
        }
    }
    
    /**
     * @Author: @
     * @Desc: 获取指定长度的list集合
     * @Date: 下午 5:40 2019/12/31 0031
     * @Param
     */
    public List<String> getList(String key,int start,int end){
        return stringRedisTemplate.opsForList().range(key,start,end);
    }
    
    /**
     * @Author: @
     * @Desc: 获取指定长度的list集合
     * @Date: 下午 5:42 2019/12/31 0031
     * @Param
     */
    public Long remove(String key,String value){
        return stringRedisTemplate.opsForList().remove(key,0,value);
    }




    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setStr("123", "123", null, null);
    }


}
