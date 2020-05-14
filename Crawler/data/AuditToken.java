16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/AuditToken.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Collections;
import java.util.List;

public class AuditToken extends UnicornStructure {

    public AuditToken(Pointer p) {
        super(p);
    }

    public int[] val = new int[8];

    @Override
    protected List<String> getFieldOrder() {
        return Collections.singletonList("val");
    }

}
