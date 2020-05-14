16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/VmCopy64Request.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class VmCopy64Request extends UnicornStructure {

    public VmCopy64Request(Pointer p) {
        super(p);
    }

    public NDR_record NDR;
    public long source_address;
    public long size;
    public long dest_address;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("NDR", "source_address", "size", "dest_address");
    }
}
