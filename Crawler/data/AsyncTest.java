50
https://raw.githubusercontent.com/iqiyi/TaskManager/master/app/src/main/java/com/qiyi/tm/demo/test/AsyncTest.java
package com.qiyi.tm.demo.test;

import android.os.Handler;


public class AsyncTest extends Test {
    public AsyncTest() {
        super();
    }

    @Override
    public void doTest() {
        getTask("AsyncTest").postAsyncDelay(1000);
    }
}
