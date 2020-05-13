78246
https://raw.githubusercontent.com/Snailclimb/JavaGuide/master/code/java/ThreadPoolExecutorDemo/src/callable/MyCallable.java
package callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //返回执行当前 Callable 的线程名字
        return Thread.currentThread().getName();
    }
}
