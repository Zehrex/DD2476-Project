11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/aspect/MtccAspectHandler.java
package com.yf.mtcc.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: 切面处理器，由具体的服务治理框架实现
 */
@FunctionalInterface
public interface MtccAspectHandler {
    Object handleAspectPointcutMethod(ProceedingJoinPoint pjp) throws Throwable;
}
