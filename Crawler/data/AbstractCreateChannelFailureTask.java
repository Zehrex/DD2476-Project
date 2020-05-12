1
https://raw.githubusercontent.com/pengfeigao/AgoraCallApi/master/call-plugin-api/src/main/java/com/basetools/task/AbstractCreateChannelFailureTask.java
package com.basetools.task;

/**
 * 创建频道失败SDK内部需要执行的操作
 * @author gaopengfei on 2019/12/05.
 */
public abstract class AbstractCreateChannelFailureTask {

    /**
     * 创建频道失败，执行回调
     */
    public abstract void run();
}
