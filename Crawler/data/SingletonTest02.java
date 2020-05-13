2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/singleton/type2/SingletonTest02.java
package com.wz.creational.singleton.type2;

/**
 * @author 隔壁老王
 * @create 2020-04-25 8:17
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
public class SingletonTest02 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println("instance1 的 hashCode 值为：" + instance1.hashCode());
        System.out.println("instance2 的 hashCode 值为：" + instance2.hashCode());
    }
}

//饿汉式（静态代码块）
class Singleton {
    //1.构造器私有化
    private Singleton(){

    }

    //2.本类内部创建对象实例
    public static final Singleton instance;

    //3.在静态代码块中创建单例对象
    static{
        instance = new Singleton();
    }

    //4.提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}