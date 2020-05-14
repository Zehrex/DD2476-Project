16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/wrapper/DvmLong.java
package com.github.unidbg.linux.android.dvm.wrapper;

import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;

public class DvmLong extends DvmObject<Long> {

    public static DvmLong valueOf(VM vm, long i) {
        return new DvmLong(vm, i);
    }

    private DvmLong(VM vm, Long value) {
        super(vm.resolveClass("java/lang/Long"), value);
    }

    @Override
    public String toString() {
        return "0x" + Long.toHexString(value) + "L";
    }
}
