11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/impl/MtccAspectHandlerServiceImpl.java
package com.yf.mtcc.core.service.impl;

import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.core.service.MtccAspectHandlerService;
import com.yf.mtcc.core.service.MtccTransactionFactoryService;
import com.yf.mtcc.core.service.MtccTransactionHandler;
import com.yf.mtcc.core.utils.SpringBeanACU;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class MtccAspectHandlerServiceImpl implements MtccAspectHandlerService {

    @Autowired
    private MtccTransactionFactoryService factoryService;

    @Override
    public Object invoke(MtccTransactionContext context, ProceedingJoinPoint pjp) throws Throwable {
        log.info("根据上下文角色获取事务处理器");
        Class handlerClass = factoryService.createHandler(context);
        log.info("获得了" + handlerClass.getName() + " 类型的事务处理器");
        MtccTransactionHandler handler = (MtccTransactionHandler) SpringBeanACU.getInstance().getBean(handlerClass);
        return handler.handle(context, pjp);
    }
}
