11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/impl/MtccTransactionFactoryServiceImpl.java
package com.yf.mtcc.core.service.impl;

import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.enums.MtccRoleEnum;
import com.yf.mtcc.common.exception.MtccException;
import com.yf.mtcc.core.service.MtccTransactionFactoryService;
import com.yf.mtcc.core.service.handler.ConsumerMtccTransactionHandler;
import com.yf.mtcc.core.service.handler.InitiatorMtccTransactionHandler;
import com.yf.mtcc.core.service.handler.ProviderMtccTransactionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class MtccTransactionFactoryServiceImpl implements MtccTransactionFactoryService {
    @Override
    public Class createHandler(MtccTransactionContext context) throws Throwable {
        if (null == context) {
            log.info("事务发起者方法....");
            return InitiatorMtccTransactionHandler.class;
        } else {
            if (context.getRole() == MtccRoleEnum.CONSUMER.getCode()) {
                log.info("事务消费者方法进来了");
                return ConsumerMtccTransactionHandler.class;
            } else if (context.getRole() == MtccRoleEnum.PROVIDER.getCode()) {
                log.info("事务提供者方法进来了");
                return ProviderMtccTransactionHandler.class;
            }
        }
        throw new MtccException("找不到事务处理类");
    }
}
