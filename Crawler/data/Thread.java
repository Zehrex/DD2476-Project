16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/api/Thread.java
package com.github.unidbg.linux.android.dvm.api;

import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;

public class Thread extends DvmObject<String> {

    public Thread(VM vm, String value) {
        super(vm.resolveClass("java/lang/Thread"), value);
    }

    public static Thread currentThread() {
        return Thread.currentThread();
    }

}
