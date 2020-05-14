16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/StringObject.java
package com.github.unidbg.linux.android.dvm;

import com.github.unidbg.memory.MemoryBlock;

public class StringObject extends DvmObject<String> {

    public StringObject(VM vm, String value) {
        super(vm.resolveClass("java/lang/String"), value);

        if (value == null) {
            //throw new NullPointerException();
            value = "";
        }
    }

    MemoryBlock memoryBlock;

    @Override
    public String toString() {
        if (value == null) {
            return null;
        } else {
            return '"' + value + '"';
        }
    }
}
