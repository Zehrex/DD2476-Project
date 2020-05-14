16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/MachMsgBody.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Collections;
import java.util.List;

public class MachMsgBody extends UnicornStructure {

    public MachMsgBody(Pointer p) {
        super(p);
    }

    public int msgh_descriptor_count;

    @Override
    protected List<String> getFieldOrder() {
        return Collections.singletonList("msgh_descriptor_count");
    }
}
