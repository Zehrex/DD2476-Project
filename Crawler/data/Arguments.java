5
https://raw.githubusercontent.com/sstewartgallus/jsystemf/master/src/com/sstewartgallus/mh/Arguments.java
package com.sstewartgallus.mh;

public interface Arguments<A extends Arguments<A>> {
    static Arguments<None> none() {
        return None.NONE;
    }

    default <B> Arguments<And<B, A>> and(Class<B> klass) {
        return new And<B, A>(klass, this);
    }

    enum None implements Arguments<None> {
        NONE
    }

    record And<A, B extends Arguments<B>>(Class<A>clazz, Arguments<B>tail) implements Arguments<And<A, B>> {
    }
}
