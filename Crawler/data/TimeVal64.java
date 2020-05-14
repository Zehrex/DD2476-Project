16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/unix/struct/TimeVal64.java
package com.github.unidbg.unix.struct;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class TimeVal64 extends UnicornStructure {

    public TimeVal64(Pointer p) {
        super(p);
    }

    public long tv_sec;
    public long tv_usec;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("tv_sec", "tv_usec");
    }

}
