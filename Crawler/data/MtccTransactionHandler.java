11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/MtccTransactionHandler.java
package com.yf.mtcc.core.service;

import com.yf.mtcc.common.context.MtccTransactionContext;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@FunctionalInterface
public interface MtccTransactionHandler {
    Object handle(MtccTransactionContext context, ProceedingJoinPoint pjp) throws Throwable;
}
