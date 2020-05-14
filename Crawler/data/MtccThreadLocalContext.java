11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/threadlocal/MtccThreadLocalContext.java
package com.yf.mtcc.core.threadlocal;

import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.domain.MtccTransaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Getter
@RequiredArgsConstructor
public enum MtccThreadLocalContext {

    INSTANCE;

    private static final ThreadLocal<MtccTransactionContext> TRANSACTION_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<MtccTransaction> TRANSACTION_THREAD_LOCAL = new ThreadLocal<>();

    public void setContext(MtccTransactionContext transactionContext) {
        TRANSACTION_CONTEXT_THREAD_LOCAL.set(transactionContext);
    }

    public MtccTransactionContext getContext() {
        return TRANSACTION_CONTEXT_THREAD_LOCAL.get();
    }

    public void setTransaction(MtccTransaction transaction) {
        TRANSACTION_THREAD_LOCAL.set(transaction);
    }

    public MtccTransaction getTransaction() {
        return TRANSACTION_THREAD_LOCAL.get();
    }

    public void removeContext() {
        TRANSACTION_CONTEXT_THREAD_LOCAL.remove();
    }

    public void removeTransaction() {
        TRANSACTION_THREAD_LOCAL.remove();
    }
}
