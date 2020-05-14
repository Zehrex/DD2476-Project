50
https://raw.githubusercontent.com/iqiyi/TaskManager/master/TaskManager/src/main/java/org/qiyi/basecore/taskmanager/deliver/ITracker.java
package org.qiyi.basecore.taskmanager.deliver;

/**
 * used to track some important logs
 * trackWaitInfo
 */
public interface ITracker {
    void track(int level, String tag, Object... msg);

    void track(Object... messages);

    void trackCritical(Object... messages);

    void printDump();

    void deliver(int type);

    boolean isDebug();

}
