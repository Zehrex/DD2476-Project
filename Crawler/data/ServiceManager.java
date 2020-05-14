16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/api/ServiceManager.java
package com.github.unidbg.linux.android.dvm.api;

import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;

public class ServiceManager extends DvmObject<String> {

    public ServiceManager(VM vm, String value) {
        super(vm.resolveClass("android/os/IServiceManager"), value);
    }

    public SystemService getService(VM vm, String serviceName) {
        return new SystemService(vm, serviceName);
    }
}
