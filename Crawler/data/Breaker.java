16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/debugger/Breaker.java
package com.github.unidbg.debugger;

import com.sun.jna.Pointer;

public interface Breaker {

    void debug();

    void brk(Pointer pc, int svcNumber);

}
