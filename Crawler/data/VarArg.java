16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/VarArg.java
package com.github.unidbg.linux.android.dvm;

public interface VarArg {

    /**
     * @param index 0 based
     */
    <T extends DvmObject<?>> T getObject(int index);

    /**
     * @param index 0 based
     */
    int getInt(int index);

}
