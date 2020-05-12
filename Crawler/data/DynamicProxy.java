2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/proxy/dynamicproxy/DynamicProxy.java
package com.wz.structural.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 隔壁老王
 * @create 2020-05-04 15:50
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//代理角色：房屋中介
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成代理类，重点是第二个参数，获取要代理的抽象角色！之前都是一个角色，现在可以代理一类角色
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        Object invoke = method.invoke(target, args);
        charge();
        return invoke;
    }

    //功能增强
    private void seeHouse(){
        System.out.println("中介带你看房");
    }
    //功能增强
    private void charge(){
        //是否收费应根据用户是否满意等进行逻辑判断，此处就不做过多处理了
        System.out.println("收取费用");
    }
}
