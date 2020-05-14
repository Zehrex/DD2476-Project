16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/unix/struct/TimeZone.java
package com.github.unidbg.unix.struct;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class TimeZone extends UnicornStructure {

    public TimeZone(Pointer p) {
        super(p);
    }

    public int tz_minuteswest;
    public int tz_dsttime;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("tz_minuteswest", "tz_dsttime");
    }

}
