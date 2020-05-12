2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/singleton/type6/SingletonTest06.java
package com.wz.creational.singleton.type6;

/**
 * @author 隔壁老王
 * @create 2020-04-25 9:03
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class SingletonTest06 {
    public static void main(String[] args) {
        System.out.println("双重检查");
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1 的 hashCode 值为：" + instance1.hashCode());
        System.out.println("instance2 的 hashCode 值为：" + instance2.hashCode());
    }
}

//双重检查
class Singleton{
    public static volatile Singleton instance;

    private Singleton() {}

    //提供一个静态的公有方法，加入双重检查代码，解决线程问题，同时解决懒加载问题
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class) {
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}







