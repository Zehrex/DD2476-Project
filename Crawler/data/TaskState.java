3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/TaskState.java
package org.coral.jroutine;

/**
 * List all possible states of the task.
 * 
 * @author lihao
 * @date 2020-05-05
 */
public enum TaskState {
    NEW, 
    RUNNABLE, 
    SUSPENDING, 
    BLOCKED, 
    WAITING, 
    TIMED_WAITING, 
    TERMINATED;
}