16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/spi/InitFunction.java
package com.github.unidbg.spi;

import com.github.unidbg.Emulator;

public abstract class InitFunction {

    protected final long load_base;
    protected final String libName;
    protected final long address;

    public InitFunction(long load_base, String libName, long address) {
        this.load_base = load_base;
        this.libName = libName;
        this.address = address;
    }

    public abstract long getAddress();

    public abstract void call(Emulator<?> emulator);

}
