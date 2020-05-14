16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/hook/hookzz/WrapCallback.java
package com.github.unidbg.hook.hookzz;

import com.github.unidbg.Emulator;
import com.github.unidbg.arm.context.RegisterContext;

public abstract class WrapCallback<T extends RegisterContext> {

    public abstract void preCall(Emulator<?> emulator, T ctx, HookEntryInfo info);

    public void postCall(Emulator<?> emulator, T ctx, HookEntryInfo info) {}

}
