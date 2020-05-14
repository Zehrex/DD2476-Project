16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/unix/struct/ITimerVal64.java
package com.github.unidbg.unix.struct;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class ITimerVal64 extends UnicornStructure {

    public ITimerVal64(Pointer p) {
        super(p);
    }

    public TimeVal64 it_interval; /* timer interval */
    public TimeVal64 it_value; /* current value */

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("it_interval", "it_value");
    }
}
