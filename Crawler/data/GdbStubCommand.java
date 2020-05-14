16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/debugger/gdb/GdbStubCommand.java
package com.github.unidbg.debugger.gdb;

import com.github.unidbg.Emulator;

interface GdbStubCommand {

    boolean processCommand(Emulator<?> emulator, GdbStub stub, String command);

}
