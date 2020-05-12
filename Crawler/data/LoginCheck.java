2
https://raw.githubusercontent.com/he303954106/AOP_Demo/master/app/src/main/java/com/netease/aop/login/annotation/LoginCheck.java
package com.netease.aop.login.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 用户登录检测
@Target(ElementType.METHOD) // 目标作用在方法之上
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
}
