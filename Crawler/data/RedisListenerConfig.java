7
https://raw.githubusercontent.com/chengxy-nds/delayqueue/master/src/main/java/com/chengxy/delayqueue/redis/rediscallback/RedisListenerConfig.java
package com.chengxy.delayqueue.redis.rediscallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Author: xinzhifu
 * @Description:
 */
@Configuration
public class RedisListenerConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}