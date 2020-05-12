2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/aspect/IntValueChangeAspect.java
package com.netease.aop.login.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.FieldSignature;

import java.lang.reflect.Field;

/**
 * Created by hk on 2020/5/9.
 */
@Aspect
public class IntValueChangeAspect {
    private static final String POINTCUT_FILEED =
            "set(int com.netease.aop.login.MainActivity.mTest) && args(newValue) && target(t)";

    @Before(POINTCUT_FILEED)
    public void onFiled(JoinPoint joinPoint, Object newValue, Object t) throws IllegalAccessException {
        Object object = joinPoint.getThis();

        FieldSignature fieldSignature = (FieldSignature) joinPoint.getSignature();
        String fileName = fieldSignature.getName();
        Field field = fieldSignature.getField();
        field.setAccessible(true);
        Class clazz = fieldSignature.getFieldType();
        String clazzName = clazz.getSimpleName();

        Object oldValue = field.get(t);

        Log.i("MainActivity", "\nonFiled value = " + newValue.toString()
                + "\n fieldSignature =" + fieldSignature.toString()
                + "\nfield = " + field.toString()
                + " +  \nFileName = " + fileName
                + "\nclazzName = " + clazzName
                + " \noldValue = " + oldValue.toString() );
    }
}
