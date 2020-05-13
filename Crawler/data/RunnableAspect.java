2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/aspect/RunnableAspect.java
package com.netease.aop.login.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hk on 2020/5/6.
 */
@Aspect
public class RunnableAspect {

    public static final String TAG = "RunnableAspect";

    @Pointcut("execution(* java.lang.Runnable.run(..))")
    public void methodPointCut() {

    }

    /**
     * 修改Toast.makeText的第一个参数为getApplicationContext,防止内存泄漏
     */
    @Around("methodPointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "RunnableAspect Method Start >>> " + Thread.currentThread().getName());
        Log.e(TAG, "RunnableAspect Method run >>> " + Log.getStackTraceString(new Throwable()));
        Log.e(TAG, "RunnableAspect Method run >>> this: " + joinPoint.getThis().getClass() + " target: " + joinPoint.getTarget().getClass());
        joinPoint.proceed();
        Log.e(TAG, "RunnableAspect Method End >>> ");
        return null;
    }

}
