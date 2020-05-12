4
https://raw.githubusercontent.com/kemusiro/jr100-emulator-v2/master/src/jp/asamomiji/assembler/TMM.java
/**
 * JR-100 Emulator Version 2
 *
 * Copyright (c) 2006-2020 Kenichi Miyata
 *
 * This software is released under the the MIT license
 * http://opensource.org/licenses/mit-license.php
 */
package jp.asamomiji.assembler;

public class TMM extends NonBranchInstruction {
    public TMM(int address, int operand) {
        super(address, Instruction.MODE_INDEXED, operand);
        mnemonic = "TMM";
    }

    @Override
    public String getOperandString() {
        switch (mode) {
        case MODE_INDEXED:
            return String.format("$%02x,X,#$%02x", (operand & 0x00ff), ((operand & 0xff00) >> 8));
        default:
            return super.getOperandString();
        }
    }
}
