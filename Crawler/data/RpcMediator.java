11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/rpc/RpcMediator.java
package com.yf.mtcc.core.rpc;

import com.alibaba.fastjson.JSON;
import com.yf.mtcc.common.context.MtccTransactionContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
public enum RpcMediator {
    INSTANCE;
    private static final String MTCC_TRANSACTION_CONTEXT = "MTCC_TRANSACTION_CONTEXT";

    public MtccTransactionContext acquire(RpcAcquire acquire) {
        MtccTransactionContext context = null;
        String json = acquire.acquire(MTCC_TRANSACTION_CONTEXT);
        if (StringUtils.isEmpty(json)) {
            return context;
        }
        context = JSON.parseObject(json, MtccTransactionContext.class);
        return context;
    }

    public void transmit(RpcTransmit transmit, MtccTransactionContext context) {
        Objects.requireNonNull(context);
        String s = JSON.toJSONString(context);
        transmit.transmit(MTCC_TRANSACTION_CONTEXT, s);
    }
}
