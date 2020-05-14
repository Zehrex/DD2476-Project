11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/schedule/MtccTransactionExecute.java
package com.yf.mtcc.core.schedule;

import com.yf.mtcc.common.config.MtccConfig;
import com.yf.mtcc.common.domain.MtccTransaction;
import com.yf.mtcc.common.enums.MtccPhaseEnum;
import com.yf.mtcc.common.enums.MtccRoleEnum;
import com.yf.mtcc.core.service.MtccTransactionLogDaoService;
import com.yf.mtcc.core.service.MtccTransactionScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Elvis on 2020/4/22
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Component
@Slf4j
public class MtccTransactionExecute implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private MtccConfig mtccConfig;

    @Autowired
    private MtccTransactionLogDaoService logDaoService;

    @Autowired
    private MtccTransactionScheduleService transactionScheduleService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread("mtcc-review-log-thread");
                t.setDaemon(true);
                return t;
            }
        });

        startSchedule(scheduledExecutorService);

    }

    private void startSchedule(ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            log.info("start transaction log review......");
            try {
                Date delayDate = new Date(System.currentTimeMillis() - mtccConfig.getScheduleDelay() * 1000);
                List<MtccTransaction> mtccTransactionList = logDaoService.selectAllByDelay(delayDate);
                for (MtccTransaction transaction : mtccTransactionList) {
                    if (transaction.getRetryTimes() > mtccConfig.getRetryMax()) {
                        log.error("处理事务日志次数超过最大重试次数，需要人工介入处理，miloTransaction:{}", transaction);
                        continue;
                    }
                    Integer phase = transaction.getPhase();
                    Integer role = transaction.getRole();
                    if (role == MtccRoleEnum.INITIATOR.getCode()) {
                        if (phase == MtccPhaseEnum.TRYING.getCode() || phase == MtccPhaseEnum.CANCELING.getCode() || phase == MtccPhaseEnum.READY.getCode()) {
                            transactionScheduleService.cancelPhase(transaction);
                        } else if (phase == MtccPhaseEnum.CONFIRMING.getCode()) {
                            transactionScheduleService.confirmPhase(transaction);
                        } else {
                            log.error("未知日志");
                        }
                    } else if (role == MtccRoleEnum.CONSUMER.getCode()) {//消费者
                        log.error("未知事务日志，miloTransaction：{}", transaction);
                    } else if (role == MtccRoleEnum.PROVIDER.getCode()) {//提供者
                        if (phase == MtccPhaseEnum.READY.getCode() || phase == MtccPhaseEnum.TRYING.getCode() || phase == MtccPhaseEnum.CANCELING.getCode()) {
                            //执行cancel流程
                            transactionScheduleService.cancelPhase(transaction);
                        } else if (phase == MtccPhaseEnum.CONFIRMING.getCode()) {
                            //执行confirm流程
                            transactionScheduleService.confirmPhase(transaction);
                        } else {
                            log.error("未知事务日志，miloTransaction：{}", transaction);
                        }
                    } else {
                        log.error("未知事务日志，miloTransaction：{}", transaction);
                    }
                }
            }catch (Exception e) {
                log.error("mtcc error", e);
            }
        }, mtccConfig.getScheduleInitDelay(), mtccConfig.getScheduleDelay(), TimeUnit.SECONDS);
    }

}
