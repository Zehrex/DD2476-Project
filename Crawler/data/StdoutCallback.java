16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/file/StdoutCallback.java
package com.github.unidbg.linux.file;

public interface StdoutCallback {

    void notifyOut(byte[] data, boolean err);

}
