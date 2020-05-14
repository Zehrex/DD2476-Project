16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/sysctl/DyldImageInfo64.java
package com.github.unidbg.ios.struct.sysctl;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class DyldImageInfo64 extends UnicornStructure {

    public DyldImageInfo64(Pointer p) {
        super(p);
    }

    public Pointer imageLoadAddress; /* base address image is mapped into */
    public Pointer imageFilePath; /* path dyld used to load the image */
    public long imageFileModDate; /* time_t of image file */

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("imageLoadAddress", "imageFilePath", "imageFileModDate");
    }
}
