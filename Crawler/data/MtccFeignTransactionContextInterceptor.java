11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-spring-cloud/src/main/java/com/yf/mtcc/springcloud/configuration/MtccFeignTransactionContextInterceptor.java
package com.yf.mtcc.springcloud.configuration;

import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.enums.MtccRoleEnum;
import com.yf.mtcc.core.rpc.RpcMediator;
import com.yf.mtcc.core.rpc.RpcTransmit;
import com.yf.mtcc.core.threadlocal.MtccThreadLocalContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;

/**
 * @Descrtption mtccFeignTransactionContextInterceptor
 * 拦截所有feign远程调用http请求，传递事务上下文（不为空时）
 * @Author Elvis
 * @Date 2019/9/26
 **/
public class MtccFeignTransactionContextInterceptor implements RequestInterceptor {

    private Logger logger;

    public MtccFeignTransactionContextInterceptor(Logger logger) {
        this.logger = logger;
    }

    /**
     * feign远程调用时，传递事务上下文
     *
     * @param requestTemplate
     */
    @Override
    public void apply(final RequestTemplate requestTemplate) {
        MtccTransactionContext context = MtccThreadLocalContext.INSTANCE.getContext();
        if (context != null && context.getRole() != MtccRoleEnum.INITIATOR.getCode()) {
            logger.info("执行feign线程name:{}", Thread.currentThread().getName());
            logger.info("========feign远程调用时，传递事务上下文start==========context:{}", context);
//            RpcTransmit rpcTransmit = (String key, String value) -> requestTemplate.header(key,value);
            RpcTransmit rpcTransmit = new RpcTransmit() {
                @Override
                public void transmit(String key, String value) {
                    requestTemplate.header(key, value);
                }
            };
            RpcMediator rpcMediator = RpcMediator.INSTANCE;
            rpcMediator.transmit(rpcTransmit, context);
            logger.info("========feign远程调用时，传递事务上下文end==========context:{}", context);
        }
    }

}
