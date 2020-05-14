11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-spring-cloud/src/main/java/com/yf/mtcc/springcloud/configuration/MtccFeignConfiguration.java
package com.yf.mtcc.springcloud.configuration;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.yf.mtcc.springcloud.hystrix.MtccHystrixStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Slf4j
@Configuration
public class MtccFeignConfiguration {

    /**
     * feign远程调用拦截器
     *
     * @return
     */
    @Bean
    @Qualifier("mtccFeignTransactionContextInterceptor")
    public MtccFeignTransactionContextInterceptor mtccFeignTransactionContextInterceptor() {
        return new MtccFeignTransactionContextInterceptor(log);
    }

    /**
     * 使用者开启的hystrix熔断功能后，
     * feign的调用使用 hystrix-cloud线程池执行远程调用，
     * 所以需要进行事务上下文的跨线程传递
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "feign.hystrix.enabled")
    public HystrixConcurrencyStrategy hystrixConcurrencyStrategy() {
        return new MtccHystrixStrategy();
    }

}
