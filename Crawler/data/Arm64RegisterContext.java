16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/arm/context/Arm64RegisterContext.java
package com.github.unidbg.arm.context;

import com.github.unidbg.pointer.UnicornPointer;

public interface Arm64RegisterContext extends RegisterContext {

    long getXLong(int index);

    int getXInt(int index);

    UnicornPointer getXPointer(int index);

    long getFp();

    UnicornPointer getFpPointer();

}
