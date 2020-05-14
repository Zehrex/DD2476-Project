16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/listener/TraceWriteListener.java
package com.github.unidbg.listener;

import com.github.unidbg.Emulator;

public interface TraceWriteListener {

    void onWrite(Emulator<?> emulator, long address, int size, long value);

}
