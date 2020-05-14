16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/test/java/com/github/unidbg/android/BusyBoxTest.java
package com.github.unidbg.android;

import java.io.File;
import java.io.IOException;

public class BusyBoxTest {

    public static void main(String[] args) throws IOException {
        RunExecutable.run(new File("src/test/resources/example_binaries/busybox"), null, "wget", "http://pv.sohu.com/cityjson?ie=utf-8", "-O", "-");
    }

}
