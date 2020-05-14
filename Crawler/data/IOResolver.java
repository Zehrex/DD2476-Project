16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/file/IOResolver.java
package com.github.unidbg.file;

import com.github.unidbg.Emulator;

public interface IOResolver<T extends NewFileIO> {

    FileResult<T> resolve(Emulator<T> emulator, String pathname, int oflags);

}
