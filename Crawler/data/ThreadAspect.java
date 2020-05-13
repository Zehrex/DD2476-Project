2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/aspect/ThreadAspect.java
//package com.netease.aop.login.aspect;
//
//import android.util.Log;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Pointcut;
//
///**
// * Created by hk on 2020/5/6.
// */
//public class ThreadAspect {
//
//    public static final String TAG = "ThreadAspect";
//
//    @Pointcut("execution(* java.lang.Thread.new(..))")
//    public void methodPointCut() {
//    }
//
//    /**
//     * 修改Toast.makeText的第一个参数为getApplicationContext,防止内存泄漏
//     */
//    @Around("methodPointCut()")
//    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "ThreadAspect Method Start >>> " + Thread.currentThread().getName());
//        joinPoint.proceed();
//        Log.e(TAG, "ThreadAspect Method End >>> ");
//        return null;
//    }
//
//
//
//}
