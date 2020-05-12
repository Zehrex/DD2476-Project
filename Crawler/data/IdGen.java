5
https://raw.githubusercontent.com/sstewartgallus/jsystemf/master/src/com/sstewartgallus/term/IdGen.java
package com.sstewartgallus.term;

public final class IdGen {
    private int argNumber = 0;

    public <A> Id<A> createId() {
        return new Id<>(argNumber++);
    }
}