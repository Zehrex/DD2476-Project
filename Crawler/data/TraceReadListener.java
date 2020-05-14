16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/listener/TraceReadListener.java
package com.github.unidbg.listener;

import com.github.unidbg.Emulator;

public interface TraceReadListener {

    void onRead(Emulator<?> emulator, long address, byte[] data, String hex);

}
