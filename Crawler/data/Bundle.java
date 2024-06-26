16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/api/Bundle.java
package com.github.unidbg.linux.android.dvm.api;

import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;
import unicorn.UnicornException;

import java.util.Properties;

public class Bundle extends DvmObject<Properties> {

    public Bundle(VM vm, Properties properties) {
        super(vm.resolveClass("android/os/Bundle"), properties);
    }

    public int getInt(String key) {
        String value = super.value.getProperty(key);
        if (value == null) {
            throw new UnicornException("key=" + key);
        }

        return Integer.parseInt(value, 16);
    }

    public String getString(String key) {
        String value = super.value.getProperty(key);
        if (value == null) {
            throw new UnicornException("key=" + key);
        }

        return value;
    }
}
