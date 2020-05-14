16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/debugger/gdb/LastSignalCommand.java
package com.github.unidbg.debugger.gdb;

import com.github.unidbg.Emulator;

class LastSignalCommand implements GdbStubCommand {

    @Override
    public boolean processCommand(Emulator<?> emulator, GdbStub stub, String command) {
        stub.makePacketAndSend("S" + GdbStub.SIGTRAP);
        return true;
    }

}
