11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/handler/InitiatorMtccTransactionHandler.java
package com.yf.mtcc.core.service.handler;

import com.alibaba.fastjson.JSON;
import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.domain.MtccInvocation;
import com.yf.mtcc.common.domain.MtccParticipant;
import com.yf.mtcc.common.domain.MtccTransaction;
import com.yf.mtcc.common.enums.MtccPhaseEnum;
import com.yf.mtcc.common.enums.MtccRoleEnum;
import com.yf.mtcc.common.exception.MtccException;
import com.yf.mtcc.core.annotation.Mtcc;
import com.yf.mtcc.core.service.MtccTransactionHandler;
import com.yf.mtcc.core.service.MtccTransactionLogDaoService;
import com.yf.mtcc.core.threadlocal.MtccThreadLocalContext;
import com.yf.mtcc.core.utils.SpringBeanACU;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: 分布式事务事务发起者处理器
 */
@Slf4j
@Service
public class InitiatorMtccTransactionHandler implements MtccTransactionHandler {
    @Autowired
    private MtccTransactionLogDaoService transactionLogDaoService;

    /**
     * Mtcc注解拦截开始
     *
     * @param context
     * @param pjp
     * @return
     */
    @Override
    public Object handle(MtccTransactionContext context, ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = null;
        try {
            log.info("开始执行try阶段...");
            returnValue = tryPhase(context, pjp);
            //tryPhase没有抛出异常，说明try阶段执行正常，接下来执行confirm
            MtccTransaction transaction = MtccThreadLocalContext.INSTANCE.getTransaction();
            log.info("开始执行confirm阶段...");
            confirmPhase(transaction);
        } catch (Throwable throwable) {
            MtccTransaction transaction = MtccThreadLocalContext.INSTANCE.getTransaction();
            cancelPhase(transaction);
//            发生异常一定要往上抛，否则会发生marked as read-only异常
            throw  throwable;
        } finally {
//
            MtccThreadLocalContext instance = MtccThreadLocalContext.INSTANCE;
            instance.removeContext();
            instance.removeTransaction();
        }
        return returnValue;
    }

    private Object cancelPhase(MtccTransaction transaction) {
        //修改发起者日志为confirm阶段
        try {
            log.info("修改cancel阶段事务日志开始");
            int i1 = transactionLogDaoService.updateTransactionPhase(transaction.getTransactionId(), MtccPhaseEnum.CANCELING.getCode());
            if (i1 == 0) {
                throw new MtccException("修改cancel阶段失败");
            }
            log.info("修改cancel阶段事务日志成功");
            List<MtccParticipant> participantList = transaction.getParticipantList();
            log.info("当前参与者信息: {}", JSON.toJSONString(participantList));
            if (CollectionUtils.isNotEmpty(participantList)) {
                List<MtccParticipant> failResult = new ArrayList<>();
                boolean isCancelResult = true;
                List<Object> successResult = new ArrayList<>();
                for (MtccParticipant participant : participantList) {
                    try {
                        //有可能当前参与者的角色是consumer，可能在业务代码中修改了上下文，这里需要重新将上下文重置
                        //如果当前参与者是发起者，那么不会发起远程调用，直接烦着调用本地方法，不会进入consumer的切面被拦截
                        MtccTransactionContext context = new MtccTransactionContext();
                        context.setRole(MtccRoleEnum.CONSUMER.getCode());
                        context.setTransactionId(transaction.getTransactionId());
                        context.setPhase(MtccPhaseEnum.CANCELING.getCode());
                        MtccThreadLocalContext.INSTANCE.setContext(context);

                        //执行目标方法
                        MtccInvocation cancelInvocation = participant.getCancelInvocation();
                        Class targetClass = cancelInvocation.getTargetClass();
                        Object bean = SpringBeanACU.getInstance().getBean(targetClass);

                        log.info("开始执行目标cancel方法, methodName: {}", cancelInvocation.getMethodName());
                        Object o = MethodUtils.invokeMethod(bean, cancelInvocation.getMethodName(), cancelInvocation.getArgs(), cancelInvocation.getParameterTypes());
                        log.info("执行目标方法成功！！");
                        successResult.add(o);
                    } catch (Exception e) {
                        log.error("有cancel方法执行失败", e);
                        failResult.add(participant);
                        isCancelResult = false;
                        e.printStackTrace();
                    } finally {
                        //set后、就要remove
                        MtccThreadLocalContext.INSTANCE.removeContext();
                    }
                }

                if (isCancelResult) {
                    int i = transactionLogDaoService.deleteByTransId(transaction.getTransactionId());
                    if (i == 0) {
                        throw new MtccException("删除事务日志失败");
                    }
                    log.info("删除事务日志成功，分布式事务数据一致性了!!");
                } else {
                    //修改参与者为失败confirm的参与者
                    log.info("开始修改失败的参与者, failResult: {}", JSON.toJSONString(failResult));
                    int i = transactionLogDaoService.updateTransactionParticipant(transaction.getTransactionId(), failResult);
                    if (i == 0) {
                        throw new MtccException("修改失败的参与者失败..");
                    }
                    log.info("修改失败的参与者成功");
                }
                if (successResult.size() > 0) {
                    return successResult.get(0);
                }
            }

        } catch (Throwable e) {
            throw e;
        }
        return null;
    }

    private Object confirmPhase(MtccTransaction transaction) {
        //修改发起者日志为confirm阶段
        try {
            log.info("开始修改事务日志当前处理阶段为confirm...");
            int i1 = transactionLogDaoService.updateTransactionPhase(transaction.getTransactionId(), MtccPhaseEnum.CONFIRMING.getCode());
            if (i1 == 0) {
                log.error("更新事务日志失败...");
                throw new MtccException("更新事务日志失败...");
            }

            List<MtccParticipant> participantList = transaction.getParticipantList();
            log.info("当前的参与者: {}", JSON.toJSONString(participantList));

            if (CollectionUtils.isNotEmpty(participantList)) {
                List<MtccParticipant> failResult = new ArrayList<>();
                boolean isConfirmResult = true;
                List<Object> successResult = new ArrayList<>();
                for (MtccParticipant participant : participantList) {
                    try {
                        //有可能当前参与者的角色是consumer，可能在业务代码中修改了上下文，这里需要重新将上下文重置
                        //如果当前参与者是发起者，那么不会发起远程调用，直接烦着调用本地方法，不会进入consumer的切面被拦截
                        MtccTransactionContext context = new MtccTransactionContext();
                        context.setRole(MtccRoleEnum.CONSUMER.getCode());
                        context.setTransactionId(transaction.getTransactionId());
                        context.setPhase(MtccPhaseEnum.CONFIRMING.getCode());
                        MtccThreadLocalContext.INSTANCE.setContext(context);

                        //执行目标方法
                        MtccInvocation confirmInvocation = participant.getConfirmInvocation();
                        Class targetClass = confirmInvocation.getTargetClass();
                        Object bean = SpringBeanACU.getInstance().getBean(targetClass);
                        log.info("开始执行参与者的confirm方法, methodName: {}", confirmInvocation.getMethodName());
                        Object o = MethodUtils.invokeMethod(bean, confirmInvocation.getMethodName(), confirmInvocation.getArgs(), confirmInvocation.getParameterTypes());
                        log.info("参与者的confirm方法执行成功!! returnValue: {}", o);
                        successResult.add(o);
                    } catch (Exception e) {
                        log.error("有参与者的confirm方法执行失败...", e);
                        failResult.add(participant);
                        isConfirmResult = false;
                        e.printStackTrace();
                    } finally {
                        MtccThreadLocalContext.INSTANCE.removeContext();
                    }
                }

                if (isConfirmResult) {
                    log.info("confirm阶段完美执行成功，开始删除事务日志....");
                    int i = transactionLogDaoService.deleteByTransId(transaction.getTransactionId());
                    if (i == 0) {
                        throw new MtccException("删除事务日志失败");
                    }
                    log.info("删除事务日志成功，分布式事务就此完成");
                } else {
                    //修改参与者为失败confirm的参与者
                    log.info("有参与者的confirm执行失败,需要修改参与者为失败了的参与者, failResult: {}", JSON.toJSONString(failResult));
                    int i = transactionLogDaoService.updateTransactionParticipant(transaction.getTransactionId(), failResult);
                    if (i == 0) {
                        log.error("更新失败的参与者失败...");
                    }
                    log.info("更新失败的额参与者成功");
                }
                if (successResult.size() > 0) {
                    return successResult.get(0);
                }
            }

        } catch (Throwable e) {
            throw e;
        }
        return null;
    }

    private Object tryPhase(MtccTransactionContext context, ProceedingJoinPoint pjp) throws Throwable {
        log.info("try phase start....");
        //1. 初始化分布式事务上下文
        //2. 初始化分布式事务参与者
        //3. 初始化分布式事务日志bean
        String transId = initTramsactionId();

        MtccTransaction transaction = new MtccTransaction();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class targetClass = pjp.getTarget().getClass();
        Object[] args = pjp.getArgs();

        Method targetMethod = signature.getMethod();
        Mtcc annotation = targetMethod.getAnnotation(Mtcc.class);

        String cancelMethod = annotation.cancelMethod();
        String confirmMethod = annotation.confirmMethod();

        transaction.setCreateTime(new Date());
        transaction.setPhase(MtccPhaseEnum.READY.getCode());
        transaction.setRole(MtccRoleEnum.INITIATOR.getCode());
        transaction.setRetryTimes(0);
        transaction.setTransactionId(transId);
        transaction.setVersion(0);
        transaction.setUpdateTime(new Date());
        transaction.setTargetClass(targetClass.getName());
        transaction.setTargetMethod(targetMethod.getName());

        MtccInvocation cancelInvocation = new MtccInvocation();
        MtccInvocation confirmInvocation = new MtccInvocation();
        if (StringUtils.isNotEmpty(cancelMethod)) {
            transaction.setCancelMethod(cancelMethod);
            cancelInvocation.setArgs(args);
            cancelInvocation.setMethodName(cancelMethod);
            cancelInvocation.setParameterTypes(targetMethod.getParameterTypes());
            cancelInvocation.setTargetClass(targetClass);
        }
        if (StringUtils.isNotEmpty(confirmMethod)) {
            transaction.setConfirmMethod(confirmMethod);
            confirmInvocation.setArgs(args);
            confirmInvocation.setMethodName(confirmMethod);
            confirmInvocation.setParameterTypes(targetMethod.getParameterTypes());
            confirmInvocation.setTargetClass(targetClass);
        }

        MtccParticipant participant = new MtccParticipant(transId, confirmInvocation, cancelInvocation);
        transaction.addParticipant(participant);
        int save = transactionLogDaoService.save(transaction);
        if (save == 0) {
            throw new MtccException("保存发起者事务日志失败");
        }
        log.info("保存发起者事务日志成功");

        MtccTransactionContext transactionContext = new MtccTransactionContext();
        transactionContext.setPhase(MtccPhaseEnum.TRYING.getCode());
        transactionContext.setRole(MtccRoleEnum.INITIATOR.getCode());
        transactionContext.setTransactionId(transId);


        //将当前线程和事务上下文绑定

        MtccThreadLocalContext.INSTANCE.setContext(transactionContext);
        MtccThreadLocalContext.INSTANCE.setTransaction(transaction);

        log.info("当前上下文环境: context: {}, transaction: {}", JSON.toJSONString(MtccThreadLocalContext.INSTANCE.getContext()), MtccThreadLocalContext.INSTANCE.getTransaction());
        //执行目标方法
        log.info("开始执行目标方法");
        Object returnValue = pjp.proceed();
        log.info("try阶段完美执行成功！！returnValue: {}", returnValue);
        //如果走到这里，代表所有参与者执行完毕，try阶段执行完成了，更新事务日志，上一步save的时候执行了保存事务
        int result = transactionLogDaoService.updateTransactionPhase(transaction.getTransactionId(), MtccPhaseEnum.TRYING.getCode());
        if (result == 0) {
            log.error("更新事务日志失败, phase: {}, role:{}", MtccPhaseEnum.TRYING.getCode(), MtccRoleEnum.INITIATOR.getCode());
            throw new MtccException("更新事务日志失败");
        }
        log.info("更新事务日志为try阶段完成");
        return returnValue;
    }

    private String initTramsactionId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
