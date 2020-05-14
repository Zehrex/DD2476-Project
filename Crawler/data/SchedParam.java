16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/SchedParam.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class SchedParam extends UnicornStructure {

    public SchedParam(Pointer p) {
        super(p);
    }

    public int sched_priority;
    public int pad;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("sched_priority", "pad");
    }
}
