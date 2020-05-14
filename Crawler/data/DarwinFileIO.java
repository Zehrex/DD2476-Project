16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/file/ios/DarwinFileIO.java
package com.github.unidbg.file.ios;

import com.github.unidbg.Emulator;
import com.github.unidbg.file.NewFileIO;
import com.github.unidbg.ios.struct.kernel.StatFS;

public interface DarwinFileIO extends NewFileIO {

    int F_GETPATH = 50; /* return the full path of the fd */

    int fstat(Emulator<?> emulator, StatStructure stat);

    int fstatfs(StatFS statFS);

}
