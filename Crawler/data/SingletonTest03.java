2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/singleton/type3/SingletonTest03.java
package com.wz.creational.singleton.type3;

/**
 * @author 隔壁老王
 * @create 2020-04-25 9:03
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        System.out.println("懒汉式1，线程不安全");
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1 的 hashCode 值为：" + instance1.hashCode());
        System.out.println("instance2 的 hashCode 值为：" + instance2.hashCode());
    }
}

// 懒汉式（线程不安全）
class Singleton{
    public static Singleton instance;

    private Singleton() {

    }

    //提供一个静态的公有方法，只有当使用到该方法时，才会去创建单例对象
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}







