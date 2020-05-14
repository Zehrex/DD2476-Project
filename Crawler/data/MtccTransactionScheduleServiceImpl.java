11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/impl/MtccTransactionScheduleServiceImpl.java
package com.yf.mtcc.core.service.impl;

import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.domain.MtccInvocation;
import com.yf.mtcc.common.domain.MtccParticipant;
import com.yf.mtcc.common.domain.MtccTransaction;
import com.yf.mtcc.common.enums.MtccPhaseEnum;
import com.yf.mtcc.common.enums.MtccRoleEnum;
import com.yf.mtcc.core.service.MtccTransactionLogDaoService;
import com.yf.mtcc.core.service.MtccTransactionScheduleService;
import com.yf.mtcc.core.threadlocal.MtccThreadLocalContext;
import com.yf.mtcc.core.utils.SpringBeanACU;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Elvis on 2020/4/22
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class MtccTransactionScheduleServiceImpl implements MtccTransactionScheduleService {
    @Autowired
    private MtccTransactionLogDaoService logDaoService;

    @Override
    public void confirmPhase(MtccTransaction transaction) {
        transaction.setRetryTimes(transaction.getRetryTimes() + 1);
        int rows = logDaoService.updateTransactionRetryTimes(transaction.getTransactionId(), transaction.getVersion(), transaction.getRetryTimes());
        if (rows == 0) {
            return;
        }

        List<MtccParticipant> participantList = transaction.getParticipantList();

        boolean confirmPhaseResult = true;
        if (CollectionUtils.isNotEmpty(participantList)) {
            List<MtccParticipant> failResult = new ArrayList<>();
            for (MtccParticipant participant : participantList) {
                try {
                    reflectExecute(participant, MtccPhaseEnum.CONFIRMING.getCode());
                } catch (Exception e) {
                    confirmPhaseResult = false;
                    failResult.add(participant);
                } finally {
                    MtccThreadLocalContext.INSTANCE.removeContext();
                }
            }
            processResult(transaction, confirmPhaseResult, failResult);
        }
    }

    @Override
    public void cancelPhase(MtccTransaction transaction) {
        transaction.setRetryTimes(transaction.getRetryTimes() + 1);
        int rows = logDaoService.updateTransactionRetryTimes(transaction.getTransactionId(), transaction.getVersion(), transaction.getRetryTimes());
        if (rows == 0) {
            return;
        }

        List<MtccParticipant> participantList = transaction.getParticipantList();

        boolean cancelPhaseResult = true;
        if (CollectionUtils.isNotEmpty(participantList)) {
            List<MtccParticipant> failResult = new ArrayList<>();
            for (MtccParticipant participant : participantList) {
                try {
                    reflectExecute(participant, MtccPhaseEnum.CANCELING.getCode());
                } catch (Exception e) {
                    cancelPhaseResult = false;
                    failResult.add(participant);
                } finally {
                    MtccThreadLocalContext.INSTANCE.removeContext();
                }
            }
            processResult(transaction, cancelPhaseResult, failResult);
        }
    }

    private void processResult(MtccTransaction transaction, boolean confirmPhaseResult, List<MtccParticipant> failResult) {
        if (confirmPhaseResult) {
//            都执行成功了，删除事务日志
            int i = logDaoService.deleteByTransId(transaction.getTransactionId());
        } else {
            logDaoService.updateTransactionParticipant(transaction.getTransactionId(), failResult);
        }
    }

    private void reflectExecute(MtccParticipant participant, int phase) throws Exception {
        MtccInvocation confirmInvocation = participant.getConfirmInvocation();
        String transactionId = participant.getTransactionId();
        if (confirmInvocation != null && transactionId != null) {
            MtccTransactionContext context = new MtccTransactionContext();
            context.setPhase(phase);
            context.setTransactionId(participant.getTransactionId());
            context.setRole(MtccRoleEnum.CONSUMER.getCode());
            MtccThreadLocalContext.INSTANCE.setContext(context);

//            执行目标方法
            Class targetClass = confirmInvocation.getTargetClass();
            Object bean = SpringBeanACU.getInstance().getBean(targetClass);
            Object[] args = confirmInvocation.getArgs();
            String methodName = confirmInvocation.getMethodName();
            MethodUtils.invokeMethod(bean, methodName, args, confirmInvocation.getParameterTypes());
        }
    }


}
