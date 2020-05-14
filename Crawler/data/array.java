16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/Array.java
package com.github.unidbg.linux.android.dvm;

import com.github.unidbg.Emulator;
import com.github.unidbg.pointer.UnicornPointer;
import com.sun.jna.Pointer;

public interface Array<T> {

    int length();

    void setData(int start, T data);

    UnicornPointer allocateMemoryBlock(Emulator<?> emulator, int length);
    void freeMemoryBlock(Pointer pointer);

}
