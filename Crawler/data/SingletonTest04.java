2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/singleton/type4/SingletonTest04.java
package com.wz.creational.singleton.type4;

/**
 * @author 隔壁老王
 * @create 2020-04-25 9:03
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class SingletonTest04 {
    public static void main(String[] args) {
        System.out.println("懒汉式2，线程安全，同步方法");
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1 的 hashCode 值为：" + instance1.hashCode());
        System.out.println("instance2 的 hashCode 值为：" + instance2.hashCode());
    }
}

//懒汉式（线程安全，同步方法）
class Singleton{
    public static Singleton instance;

    private Singleton() {

    }

    //提供一个静态的公有方法，加入了同步处理的代码，解决线程安全问题
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}







