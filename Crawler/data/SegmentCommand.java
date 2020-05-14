16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/SegmentCommand.java
package com.github.unidbg.ios.struct;

import com.sun.jna.Pointer;

public abstract class SegmentCommand extends LoadCommand {

    public SegmentCommand(Pointer p) {
        super(p);
    }

    public byte[] segname = new byte[16];

    public String getSegName() {
        return new String(segname).trim();
    }

    public abstract long getVmAddress();

}
