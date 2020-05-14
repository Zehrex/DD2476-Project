16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/MachTimebaseInfo.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class MachTimebaseInfo extends UnicornStructure {

    public MachTimebaseInfo(Pointer p) {
        super(p);
    }

    public int numer;
    public int denom;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("numer", "denom");
    }
}
