11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/aspect/AbstractMtccAspect.java
package com.yf.mtcc.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Aspect
public abstract class AbstractMtccAspect {

    private MtccAspectHandler aspectHandler;

    protected void setAspectHandler(MtccAspectHandler aspectHandler) {
        this.aspectHandler = aspectHandler;
    }

    @Pointcut("@annotation(com.yf.mtcc.core.annotation.Mtcc)")
    public void interceptPoint() {
    }


    @Around("interceptPoint()")
    public Object interceptPointHandlerMethod(final ProceedingJoinPoint pjp) throws Throwable {
        return aspectHandler.handleAspectPointcutMethod(pjp);
    }

}
