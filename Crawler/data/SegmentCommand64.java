16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/SegmentCommand64.java
package com.github.unidbg.ios.struct;

import com.sun.jna.Pointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentCommand64 extends SegmentCommand {

    public SegmentCommand64(Pointer p) {
        super(p);
    }

    public long vmaddr;

    @Override
    public long getVmAddress() {
        return vmaddr;
    }

    @Override
    protected List<String> getFieldOrder() {
        List<String> list = new ArrayList<>(super.getFieldOrder());
        Collections.addAll(list, "segname", "vmaddr");
        return list;
    }
}
