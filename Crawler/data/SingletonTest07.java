2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/singleton/type7/SingletonTest07.java
package com.wz.creational.singleton.type7;

/**
 * @author 隔壁老王
 * @create 2020-04-25 9:03
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        System.out.println("静态内部类");
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1 的 hashCode 值为：" + instance1.hashCode());
        System.out.println("instance2 的 hashCode 值为：" + instance2.hashCode());
    }
}

//静态内部类
class Singleton{

    private Singleton() {}

    //静态内部类：当Singleton被装载的时候，静态内部类是不会被装载的
    private static class SingletonInstance {
        public static final Singleton INSTANCE = new Singleton();
    }

    //当调用getInstance方法时，用到了SingletonInstance的INSTANCE静态变量，
    //会导致静态内部类进行装载，类进行装载时，线程是安全的
    public static Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}







