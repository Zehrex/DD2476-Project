11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/handler/ConsumerMtccTransactionHandler.java
package com.yf.mtcc.core.service.handler;

import com.alibaba.fastjson.JSON;
import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.domain.MtccInvocation;
import com.yf.mtcc.common.domain.MtccParticipant;
import com.yf.mtcc.common.domain.MtccTransaction;
import com.yf.mtcc.common.enums.MtccPhaseEnum;
import com.yf.mtcc.core.service.MtccTransactionHandler;
import com.yf.mtcc.core.service.MtccTransactionLogDaoService;
import com.yf.mtcc.core.threadlocal.MtccThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: Elvis on 2020/4/14
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class ConsumerMtccTransactionHandler implements MtccTransactionHandler {
    @Autowired
    private MtccTransactionLogDaoService logDaoService;

    @Override
    public Object handle(MtccTransactionContext context, ProceedingJoinPoint pjp) throws Throwable {
        if (context.getPhase() == MtccPhaseEnum.TRYING.getCode()) {
            log.info("开始执行消费者的try阶段方法，记录当前参与者");
            //try阶段，记录参与者
            MtccTransaction transaction = MtccThreadLocalContext.INSTANCE.getTransaction();
            List<MtccParticipant> participantList = transaction.getParticipantList();
            log.info("当前的参与者: {}", JSON.toJSONString(participantList));
            MtccInvocation invocation = new MtccInvocation();
            //这个是代理的对象，应该得到的是decalaringClass
            Class<?> targetClass = pjp.getTarget().getClass();
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();

            invocation.setTargetClass(method.getDeclaringClass());
            invocation.setParameterTypes(method.getParameterTypes());
            invocation.setMethodName(method.getName());
            invocation.setArgs(pjp.getArgs());

            MtccParticipant participant = new MtccParticipant(transaction.getTransactionId(), invocation, invocation);
            participantList.add(participant);
            log.info("更新后的参与者: {}", JSON.toJSONString(participantList));
            logDaoService.updateTransactionParticipant(transaction.getTransactionId(), participantList);
            return pjp.proceed();
        } else if (context.getPhase() == MtccPhaseEnum.CONFIRMING.getCode()) {
            log.info("调用feign接口执行提供者的confirm方法");
            return pjp.proceed();
        } else if (context.getPhase() == MtccPhaseEnum.CANCELING.getCode()) {
            log.info("调用feign接口执行提供者的cancel方法");
            return pjp.proceed();
        }
        return null;
    }
}
