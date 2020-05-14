16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/arm/context/EditableArm64RegisterContext.java
package com.github.unidbg.arm.context;

import com.sun.jna.Pointer;

public interface EditableArm64RegisterContext extends Arm64RegisterContext {

    void setXLong(int index, long value);

    void setStackPointer(Pointer sp);

}
