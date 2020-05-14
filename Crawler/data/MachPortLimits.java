16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/MachPortLimits.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Collections;
import java.util.List;

public class MachPortLimits extends UnicornStructure {

    public MachPortLimits(Pointer p) {
        super(p);
    }

    public int mpl_qlimit; /* number of msgs */

    @Override
    protected List<String> getFieldOrder() {
        return Collections.singletonList("mpl_qlimit");
    }
}
