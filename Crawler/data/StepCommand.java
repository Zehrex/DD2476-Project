16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/debugger/gdb/StepCommand.java
package com.github.unidbg.debugger.gdb;

import com.github.unidbg.Emulator;

class StepCommand implements GdbStubCommand {

    @Override
    public boolean processCommand(Emulator<?> emulator, GdbStub stub, String command) {
        stub.singleStep();
        stub.makePacketAndSend("OK");
        return true;
    }

}
