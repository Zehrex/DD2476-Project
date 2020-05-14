16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/listener/TraceCodeListener.java
package com.github.unidbg.listener;

import capstone.Capstone;
import com.github.unidbg.Emulator;

public interface TraceCodeListener {

    void onInstruction(Emulator<?> emulator, long address, Capstone.CsInsn insn);

}
