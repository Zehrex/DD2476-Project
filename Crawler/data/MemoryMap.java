16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/memory/MemoryMap.java
package com.github.unidbg.memory;

public class MemoryMap {

    public final long base;
    public final long size;
    public final int prot;

    public MemoryMap(long base, long size, int prot) {
        this.base = base;
        this.size = size;
        this.prot = prot;
    }

    @Override
    public String toString() {
        return "MemoryMap{" +
                "base=0x" + Long.toHexString(base) +
                ", size=" + size +
                ", prot=" + prot +
                '}';
    }
}
