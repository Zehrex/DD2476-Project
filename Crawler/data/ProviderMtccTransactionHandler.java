11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/handler/ProviderMtccTransactionHandler.java
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
import com.yf.mtcc.core.utils.SpringBeanACU;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Elvis on 2020/4/14
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class ProviderMtccTransactionHandler implements MtccTransactionHandler {

    private MtccTransactionLogDaoService logDaoService;

    public ProviderMtccTransactionHandler(MtccTransactionLogDaoService logDaoService) {
        this.logDaoService = logDaoService;
    }

    @Override
    public Object handle(MtccTransactionContext context, ProceedingJoinPoint pjp) throws Throwable {
        //判断当前阶段
        log.info("来到提供者的处理器");
        int phase = context.getPhase();
        log.info("当前阶段: {}", MtccPhaseEnum.acquireByCode(phase));
        if (phase == MtccPhaseEnum.TRYING.getCode()) {

            log.info("提供者try阶段处理开始...");
            //记录日志
            int save = 0;
            try {
                //当保存事务日志失败了，本地事务却操作成功了，这两个事务是独立的，如果不往外抛异常，导致事务日志没有记录，但是本地事务却执行成功了，但此时处在try阶段
                //考虑这样一种场景：调用用户账户服务超时了，发起者会执行cancel阶段，库存服务假设正常，cancel了，但是到了账户服务，由于发现没有日志记录，无法知道账户服务到底成功了还是失败了
                MtccTransaction transaction = new MtccTransaction();

                Class<?> targetClass = pjp.getTarget().getClass();
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                Method method = signature.getMethod();
                Mtcc annotation = method.getAnnotation(Mtcc.class);
                String confirmMethod = annotation.confirmMethod();
                String cancelMethod = annotation.cancelMethod();


                transaction.setTransactionId(context.getTransactionId());
                transaction.setPhase(MtccPhaseEnum.READY.getCode());
                transaction.setRole(MtccRoleEnum.PROVIDER.getCode());
                transaction.setRetryTimes(0);
                transaction.setVersion(0);
                transaction.setTargetClass(targetClass.getName());
                transaction.setTargetMethod(method.getName());

                MtccParticipant participant = new MtccParticipant();
                MtccInvocation confirmInvocation = null;
                MtccInvocation cancelInvocation = null;
                if (StringUtils.isNotEmpty(confirmMethod)) {
                    transaction.setConfirmMethod(confirmMethod);
                    confirmInvocation = new MtccInvocation(targetClass, confirmMethod, pjp.getArgs(), method.getParameterTypes());
                    participant.setConfirmInvocation(confirmInvocation);
                }
                if (StringUtils.isNotEmpty(cancelMethod)) {
                    transaction.setCancelMethod(cancelMethod);
                    cancelInvocation = new MtccInvocation(targetClass, cancelMethod, pjp.getArgs(), method.getParameterTypes());
                    participant.setCancelInvocation(cancelInvocation);
                }
                participant.setTransactionId(transaction.getTransactionId());

                transaction.setCreateTime(new Date());
                transaction.setUpdateTime(new Date());
                transaction.addParticipant(participant);
                log.info("开始记录事务日志, transaction: {}", JSON.toJSONString(transaction));
                save = logDaoService.save(transaction);
                if (save == 0) {
                    log.error("记录事务日志失败");
                    throw new MtccException("保存事务日志失败");
                }

                //执行本地事务
                log.info("开始执行本地事务方法了");
                Object proceed = pjp.proceed();
                log.info("提供者try阶段执行成功了！！！ returnValue: {}", proceed);
                return proceed;
            } catch (Throwable e) {
                //往外抛，
                throw e;
            } finally {
                if (save != 0) {
                    log.info("修改事务日志处理阶段为try");
                    //修改阶段为trying
                    logDaoService.updateTransactionPhase(context.getTransactionId(), MtccPhaseEnum.TRYING.getCode());
                }
            }
        } else if (phase == MtccPhaseEnum.CONFIRMING.getCode()) {
            log.info("提供者confirm阶段处理开始...");
            //从事务日志中查询所有的参与者
            try {
                MtccTransaction transaction = logDaoService.selectByTransId(context.getTransactionId());
                if (null != transaction) {
                    if (transaction.getPhase() == MtccPhaseEnum.READY.getCode()) {
                        throw new MtccException("try阶段还没有执行完成就收到confirm指令");
                    }
                    int i1 = logDaoService.updateTransactionPhase(context.getTransactionId(), phase);
                    if (i1 == 0) {
                        log.error("修改cancel阶段事务日志失败");
                        throw new MtccException("修改cancel阶段事务日志失败");
                    }
                    Object confirmResult = null;
                    boolean isConfirmResult = true;

                    List<MtccParticipant> participantList = transaction.getParticipantList();
                    log.info("提供者的confirm阶段的参与者: {}", JSON.toJSONString(participantList));
                    if (CollectionUtils.isNotEmpty(participantList)) {
                        List<Object> successResult = new ArrayList<>();
                        List<MtccParticipant> failResult = new ArrayList<>();
                        for (MtccParticipant participant : participantList) {
                            try {
                                MtccInvocation confirmInvocation = participant.getConfirmInvocation();
                                //执行目标方法
                                Class targetClass = confirmInvocation.getTargetClass();
                                Object bean = SpringBeanACU.getInstance().getBean(targetClass);
                                log.info("开始执行提供者的confirm方法... methodName: {}", confirmInvocation.getMethodName());
                                Object o = MethodUtils.invokeMethod(bean, confirmInvocation.getMethodName(), confirmInvocation.getArgs(), confirmInvocation.getParameterTypes());
                                log.info("提供者的confirm方法执行成功!!!");
                                successResult.add(o);
                            } catch (Exception e) {
                                log.error("提供者的confirm方法执行失败了..", e);
                                failResult.add(participant);
                                isConfirmResult = false;
                                e.printStackTrace();
                            }
                        }
                        if (isConfirmResult) {
                            //删除日志
                            log.info("开始删除提供者日志...");
                            int i = logDaoService.deleteByTransId(context.getTransactionId());
                            if (i == 0) {
                                throw new MtccException("删除事务日志失败");
                            }
                            log.info("删除提供者日志成功");
                        } else {
                            //更新参与者为失败的参与者
                            logDaoService.updateTransactionParticipant(context.getTransactionId(), failResult);
                        }

                        if (successResult.size() > 0) {
                            return successResult.get(0);
                        }
                    }

                }
                return null;
            } catch (Throwable e) {
                throw e;
            }
        } else {
            log.info("提供者cancel阶段处理开始...");
            try {
                MtccTransaction transaction = logDaoService.selectByTransId(context.getTransactionId());
                if (transaction != null) {
                    if (transaction.getPhase() == MtccPhaseEnum.READY.getCode()) {
                        throw new MtccException("try阶段还没有执行完成就收到confirm指令");
                    }

                    int i1 = logDaoService.updateTransactionPhase(context.getTransactionId(), phase);
                    if (i1 == 0) {
                        log.error("执行提供者的更新事务阶段cancel失败");
                        throw new MtccException("执行提供者的cancel方法失败");
                    }
                    Object cancelResult = null;
                    boolean isCancelResult = true;

                    List<MtccParticipant> participantList = transaction.getParticipantList();
                    log.info("当前的参与者: {}", JSON.toJSONString(participantList));
                    if (CollectionUtils.isNotEmpty(participantList)) {
                        List<Object> successResult = new ArrayList<>();
                        List<MtccParticipant> failResult = new ArrayList<>();
                        for (MtccParticipant participant : participantList) {
                            try {
                                MtccInvocation cancelInvocation = participant.getCancelInvocation();
                                //执行目标方法
                                Class targetClass = cancelInvocation.getTargetClass();
                                Object bean = SpringBeanACU.getInstance().getBean(targetClass);
                                Object o = MethodUtils.invokeMethod(bean, cancelInvocation.getMethodName(), cancelInvocation.getArgs(), cancelInvocation.getParameterTypes());
                                log.info("执行提供者的cancel方法成功");
                                successResult.add(o);
                            } catch (Exception e) {
                                failResult.add(participant);
                                isCancelResult = false;
                                e.printStackTrace();
                            }
                        }
                        if (isCancelResult) {
                            //删除日志
                            int i = logDaoService.deleteByTransId(context.getTransactionId());
                            if (i == 0) {
                                throw new MtccException("删除事务日志失败");
                            }
                            log.info("删除事务日志成功");
                        } else {
                            //更新参与者为失败的参与者
                            logDaoService.updateTransactionParticipant(context.getTransactionId(), failResult);
                        }
                        if (successResult.size() > 0) {
                            return successResult.get(0);
                        }
                    }
                }
                return null;
            } catch (Throwable e) {
                throw e;
            }
        }
    }
}
