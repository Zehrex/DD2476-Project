16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/classdump/IClassDumper.java
package com.github.unidbg.ios.classdump;

import com.github.unidbg.hook.IHook;

public interface IClassDumper extends IHook {

    String dumpClass(String className);

    void searchClass(String keywords);

}
