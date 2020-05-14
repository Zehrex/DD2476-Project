16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/hook/HookListener.java
package com.github.unidbg.hook;

import com.github.unidbg.memory.SvcMemory;

public interface HookListener {

    /**
     * 返回0表示没有hook，否则返回hook以后的调用地址
     */
    long hook(SvcMemory svcMemory, String libraryName, String symbolName, long old);

}
