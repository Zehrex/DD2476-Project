2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/singleton/type8/SingletonTest08.java
package com.wz.creational.singleton.type8;

/**
 * @author 隔壁老王
 * @create 2020-04-25 10:16
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class SingletonTest08 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance1 == instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

        instance1.sayOk();
    }
}

//使用枚举可以实现单例，推荐使用
enum Singleton{
    //枚举类型中的每个属性的类型都是枚举类型的，因此每个属性都是一个单例
    //抗反射，不管是懒汉式还是俄汉式通过反射都可以变成多例，枚举抗反射

    INSTANCE;   //属性

    public void sayOk() {
        System.out.println("OK~~~");
    }
}























