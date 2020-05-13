2
https://raw.githubusercontent.com/pi-181/num-methods-lab6/master/src/main/java/com/demkom58/divine/util/Triple.java
package com.demkom58.divine.util;

public class Triple<A, B, C> {
    private final A first;
    private final B second;
    private final C third;

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }
}