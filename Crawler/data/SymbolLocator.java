16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/net/fornwall/jelf/SymbolLocator.java
package net.fornwall.jelf;

import java.io.IOException;

public interface SymbolLocator {

    ElfSymbol getELFSymbol(int index) throws IOException;

    ElfSymbol getELFSymbolByName(String name) throws IOException;

}
