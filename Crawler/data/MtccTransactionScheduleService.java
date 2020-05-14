11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/MtccTransactionScheduleService.java
package com.yf.mtcc.core.service;

import com.yf.mtcc.common.domain.MtccTransaction;

public interface MtccTransactionScheduleService {

    void confirmPhase(MtccTransaction transaction);

    void cancelPhase(MtccTransaction transaction);

}
