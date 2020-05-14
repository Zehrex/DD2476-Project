16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/NeedLibrary.java
package com.github.unidbg.ios;

class NeedLibrary {

    final String path;
    final boolean upward;
    NeedLibrary(String path, boolean upward) {
        this.path = path;
        this.upward = upward;
    }

    @Override
    public String toString() {
        return (upward ? '?' : '*') + path;
    }
}
