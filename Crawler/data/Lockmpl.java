2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/Lock/Lockmpl.java
package java2.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票案例出现了线程安全问题
 * 卖出了不存在的票和重复的票
 *
 * 解决线程安全问题的三种方法：使用Lock锁
 * java.util.concurrent.locks.lock接口
 * Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作
 * Lock 接口中的方法：
 *      void  lcok() 获取锁
 *      void  unlock() 释放锁
 * java.util.concurrent.locks.ReentrantLock implements lock 接口
 *
 *
 * 使用步骤：
 *      1、在成员位置创建一个ReentrantLock对象
 *      2、在可能出现安全问题的代码前调用Lock接口中的lock获取锁
 *      3、在可能出现安全问题的代码后调用Lock接口中的unlock释放锁
 */
public class Lockmpl implements Runnable{
    //定义一个多个线程共享的票源
    private int ticket = 10000;

    //1、在成员位置创建一个ReentrantLock对象
    Lock l = new ReentrantLock();

    @Override
    public void run() {
        //使用死循环，让卖票重复执行
        while (true) {
            //2、在可能出现安全问题的代码前调用Lock接口中的lock获取锁
            l.lock();

            //先判断票是否存在
            if(ticket > 0){
                try {
                    Thread.sleep(10);

                    System.out.println(Thread.currentThread().getName() + "-->正在卖第" + ticket + "张票");
                    ticket--;
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    //3、在可能出现安全问题的代码后调用Lock接口中的unlock释放锁
                    l.unlock();
                }
            }
        }
    }
}
