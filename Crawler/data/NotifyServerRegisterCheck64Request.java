16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/NotifyServerRegisterCheck64Request.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class NotifyServerRegisterCheck64Request extends UnicornStructure {

    public NotifyServerRegisterCheck64Request(Pointer p) {
        super(p);
    }

    public int pad1;
    public int nameLow;
    public int nameHigh;
    public int pad2;
    public int namelen;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("pad1", "nameLow", "nameHigh", "pad2", "namelen");
    }

}
