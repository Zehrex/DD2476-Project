16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/debugger/ida/DebuggerEvent.java
package com.github.unidbg.debugger.ida;

import com.github.unidbg.Emulator;

public abstract class DebuggerEvent {

    public abstract byte[] pack(Emulator<?> emulator);

}
