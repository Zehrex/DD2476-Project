11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/MtccTransactionLogDaoService.java
package com.yf.mtcc.core.service;

import com.yf.mtcc.common.config.MtccConfig;
import com.yf.mtcc.common.domain.MtccParticipant;
import com.yf.mtcc.common.domain.MtccTransaction;

import java.util.Date;
import java.util.List;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
public interface MtccTransactionLogDaoService {
    int save(MtccTransaction transaction);

    void init(MtccConfig mtccConfig);


    int updateTransactionPhase(String transactionId, int phase);

    int updateTransactionParticipant(String transactionId, List<MtccParticipant> participantList);

    MtccTransaction selectByTransId(String transactionId);

    int deleteByTransId(String transactionId);

    List<MtccTransaction> selectAllByDelay(Date delayDate);

    int updateTransactionRetryTimes(String transactionId, Integer version, Integer retryTimes);
}
