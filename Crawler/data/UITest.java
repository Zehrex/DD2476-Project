50
https://raw.githubusercontent.com/iqiyi/TaskManager/master/app/src/main/java/com/qiyi/tm/demo/test/UITest.java
package com.qiyi.tm.demo.test;



/**
 * test ui & test delay
 */
public class UITest extends Test {
    public void doTest() {
        getTask().postUIDelay(1000);
    }


}
