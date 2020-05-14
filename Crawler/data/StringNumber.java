16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/StringNumber.java
package com.github.unidbg;

public class StringNumber extends Number {

    public final String value;

    public StringNumber(String value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        throw new AbstractMethodError();
    }

    @Override
    public long longValue() {
        throw new AbstractMethodError();
    }

    @Override
    public float floatValue() {
        throw new AbstractMethodError();
    }

    @Override
    public double doubleValue() {
        throw new AbstractMethodError();
    }

    @Override
    public String toString() {
        return value;
    }
}
