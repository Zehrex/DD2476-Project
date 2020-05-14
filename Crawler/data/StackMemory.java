16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/memory/StackMemory.java
package com.github.unidbg.memory;

import com.github.unidbg.pointer.UnicornPointer;

public interface StackMemory {

    UnicornPointer writeStackString(String str);
    UnicornPointer writeStackBytes(byte[] data);

}
