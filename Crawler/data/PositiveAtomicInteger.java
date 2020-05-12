3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/schedule/lb/PositiveAtomicInteger.java
package org.coral.jroutine.schedule.lb;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * get a value that must be a positive number.
 * 
 * @author lihao
 * @date 2020-05-12
 */
public final class PositiveAtomicInteger {

    private static final int MASK = 0x7FFFFFFF;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int incrementAndGet() {
        return atomicInteger.incrementAndGet() & MASK;
    }

    public int getAndIncrement() {
        return atomicInteger.getAndIncrement() & MASK;
    }
}
