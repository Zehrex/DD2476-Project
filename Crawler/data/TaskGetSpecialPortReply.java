16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/TaskGetSpecialPortReply.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class TaskGetSpecialPortReply extends UnicornStructure {

    public TaskGetSpecialPortReply(Pointer p) {
        super(p);
    }

    public MachMsgBody body;
    public MachMsgPortDescriptor port;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("body", "port");
    }

}
