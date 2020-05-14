16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/arm/ThumbSvc.java
package com.github.unidbg.arm;

import com.github.unidbg.Emulator;
import com.github.unidbg.Svc;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.pointer.UnicornPointer;
import keystone.KeystoneMode;

public abstract class ThumbSvc implements Svc {

    @Override
    public UnicornPointer onRegister(SvcMemory svcMemory, int svcNumber) {
        if (svcNumber > 0xff) {
            throw new IllegalStateException();
        }

        return ArmSvc.register(svcMemory, svcNumber, KeystoneMode.ArmThumb);
    }

    @Override
    public void handleCallback(Emulator<?> emulator) {
    }

}
