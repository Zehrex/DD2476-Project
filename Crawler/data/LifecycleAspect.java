2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/aspect/LifecycleAspect.java
package com.netease.aop.login.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by hk on 2020/5/9.
 */
@Aspect
public class LifecycleAspect {

    private static final String POINTCUT_ON_METHOD =
            "execution(* android.app.Activity.on**(..))";

//    @Before(POINTCUT_ON_METHOD)
//    public void beforeOnMethod(JoinPoint joinPoint) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//        Log.i(className, "before " + methodName + " log");
//
//    }
//
//    @After(POINTCUT_ONMETHOD)
//    public void onMethLog(JoinPoint joinPoint) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//        Log.i(className, "after " + methodName + " log");
//    }


    @Pointcut(POINTCUT_ON_METHOD)
    public void annotationOnMethodTrace(){

    }

    @Around("annotationOnMethodTrace()")
    public Object weaveOnMethodJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Log.i(className, "before joinPoint proceed className = " + className + " methodName = " + methodName);

        Object result  = joinPoint.proceed();
        Log.i(className, "after joinPoint proceed className = " + className + " methodName = " + methodName);

        return result;
    }
}
