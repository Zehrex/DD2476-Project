2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/aspect/NoDoubleClickAspect.java
package com.netease.aop.login.aspect;

import android.util.Log;

import com.netease.aop.login.NoDoubleClickUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * Created by hk on 2020/5/9.
 */
public class NoDoubleClickAspect {

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void onClickLitener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.e("NoDoubleClickAspect", "OnClick");
        if (!NoDoubleClickUtils.isDoubleClick()) {
            proceedingJoinPoint.proceed();
        }
    }
}
