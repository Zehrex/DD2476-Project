2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/aspect/ToastAspect.java
package com.netease.aop.login.aspect;

import android.util.Log;

import com.netease.aop.login.MyApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hk on 2020/5/6.
 */
@Aspect
public class ToastAspect {

    public static final String TAG = "ToastAspect";

    @Pointcut("call(* android.widget.Toast.makeText(..))")
    public void methodPointCut() {

    }

    /**
     * 修改Toast.makeText的第一个参数为getApplicationContext,防止内存泄漏
     */
    @Around("methodPointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "Toast Method Start >>> ");
        Object[] args = joinPoint.getArgs();
        args[0] = MyApplication.getApplication();
        Object result = joinPoint.proceed(args);
        Log.e(TAG, "Toast Method End >>> " + args[1]);
        return result;
    }
}
