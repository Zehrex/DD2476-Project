16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/ipa/IpaLoaderImpl.java
package com.github.unidbg.ios.ipa;

import com.github.unidbg.Emulator;
import com.github.unidbg.Module;

class IpaLoaderImpl extends IpaLoader {

    private final Emulator<?> emulator;
    private final Module executable;

    IpaLoaderImpl(Emulator<?> emulator, Module executable) {
        this.emulator = emulator;
        this.executable = executable;
    }

    @Override
    public void callEntry() {
        executable.callEntry(emulator);
    }

    @Override
    public Module getExecutable() {
        return executable;
    }

    @Override
    public Emulator<?> getEmulator() {
        return emulator;
    }
}
