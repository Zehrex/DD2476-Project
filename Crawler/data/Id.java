5
https://raw.githubusercontent.com/sstewartgallus/jsystemf/master/src/com/sstewartgallus/term/Id.java
package com.sstewartgallus.term;

public record Id<A>(int number) implements Comparable<Id<?>> {
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(Id<?> var) {
        return var.number - number;
    }
}
