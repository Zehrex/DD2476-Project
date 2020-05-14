16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/LibraryResolver.java
package com.github.unidbg;

import com.github.unidbg.spi.LibraryFile;

public interface LibraryResolver {

    LibraryFile resolveLibrary(Emulator<?> emulator, String libraryName);

}
