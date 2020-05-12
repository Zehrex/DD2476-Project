2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Instantiable/Math/Vec4.java
package com.naive.phase.Auxiliary.Instantiable.Math;

public class Vec4<T> {
    private final T r;
    private final T g;
    private final T b;
    private final T a;

    public Vec4(T r, T g, T b, T a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public T getG() {
        return g;
    }

    public T getR() {
        return r;
    }

    public T getX() {
        return r;
    }

    public T getY() {
        return g;
    }

    public T getZ() {
        return b;
    }

    public T getW() {
        return a;
    }
}
