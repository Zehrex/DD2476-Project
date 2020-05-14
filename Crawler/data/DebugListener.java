16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/debugger/DebugListener.java
package com.github.unidbg.debugger;

import com.github.unidbg.Emulator;
import com.github.unidbg.arm.CodeHistory;

public interface DebugListener {

    boolean canDebug(Emulator<?> emulator, CodeHistory currentCode);

}
