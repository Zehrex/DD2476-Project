2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/Lock/DemoLock.java
package java2.Lock;

public class DemoLock {
    public static void main(String[] args) {
        //创建 Runnable 接口的实现类对象
        Lockmpl run = new Lockmpl();
        //创建Thread类对象，构造方法中传递Runnable接口的实现类对象
        Thread t0 = new Thread(run);
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        //调用start方法开启多线程
        t0.start();
        t1.start();
        t2.start();
    }
}
