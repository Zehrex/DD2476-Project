16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/HostInfoReply.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class HostInfoReply extends UnicornStructure {

    public HostInfoReply(Pointer p) {
        super(p);
    }

    public NDR_record NDR;
    public int retCode;
    public int host_info_outCnt = 8;
    public HostInfo host_info_out;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("NDR", "retCode", "host_info_outCnt", "host_info_out");
    }

}
