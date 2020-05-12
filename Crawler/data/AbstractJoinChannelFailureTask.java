1
https://raw.githubusercontent.com/pengfeigao/AgoraCallApi/master/call-plugin-api/src/main/java/com/basetools/task/AbstractJoinChannelFailureTask.java
package com.basetools.task;

/**
 * 加入频道失败SDK内部需要执行的操作
 * @author gaopengfei on 2019/12/05.
 */
public abstract class AbstractJoinChannelFailureTask {
    /**
     * 加入频道失败，执行操作
     */
    public abstract void run();
}
