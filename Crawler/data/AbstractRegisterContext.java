16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/arm/context/AbstractRegisterContext.java
package com.github.unidbg.arm.context;

import com.github.unidbg.pointer.UnicornPointer;

public abstract class AbstractRegisterContext implements RegisterContext {

    @Override
    public final int getIntArg(int index) {
        return (int) getLongArg(index);
    }

    @Override
    public final long getLongArg(int index) {
        UnicornPointer pointer = getPointerArg(index);
        return pointer == null ? 0 : pointer.peer;
    }

}
