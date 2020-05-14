16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/StackTraceElement.java
package com.github.unidbg.linux.android.dvm;


public class StackTraceElement extends DvmObject<String> {

    public StackTraceElement(VM vm, String value) {
        super(vm.resolveClass("java/lang/StackTraceElement"), value);

    }

    public String getClassName() {
        return this.value;
    }

}