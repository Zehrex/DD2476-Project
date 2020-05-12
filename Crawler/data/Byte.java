2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/codec/Byte.java
package com.nitro.core.communication.codec;

public class Byte {

    public static int BYTE_MAX_VALUE = 127;

    private int value;

    public Byte(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
