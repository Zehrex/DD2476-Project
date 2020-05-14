16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/memory/MemoryBlock.java
package com.github.unidbg.memory;

import com.github.unidbg.pointer.UnicornPointer;
import com.sun.jna.Pointer;

public interface MemoryBlock {

    UnicornPointer getPointer();

    boolean isSame(Pointer pointer);

    void free(boolean runtime);

}
