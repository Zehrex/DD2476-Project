3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/schedule/Scheduler.java
package org.coral.jroutine.schedule;

/**
 * the interface of scheduler.
 * 
 * @author lihao
 * @date 2020-05-12
 */
public interface Scheduler<T extends Runnable> {

    public void submit(T t);
}
