2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/18.%E3%80%90%E7%BA%BF%E7%A8%8B%E6%B1%A0%E3%80%81Lambda%E8%A1%A8%E8%BE%BE%E5%BC%8F%E3%80%91-%E7%AC%94%E8%AE%B0/code/07_ThreadAndLambda/src/com/itheima/demo03/Lambda/RunnableImpl.java
package com.itheima.demo03.Lambda;
/*
    创建Runnable接口的实现类,重写run方法,设置线程任务
 */
public class RunnableImpl implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 新线程创建了");
    }
}
