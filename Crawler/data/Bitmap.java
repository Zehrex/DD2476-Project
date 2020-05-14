16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/linux/android/dvm/api/Bitmap.java
package com.github.unidbg.linux.android.dvm.api;

import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.MemoryBlock;

import java.awt.image.BufferedImage;

public class Bitmap extends DvmObject<BufferedImage> {

    public Bitmap(VM vm, BufferedImage image) {
        super(vm.resolveClass("android/graphics/Bitmap"), image);
    }

    public MemoryBlock memoryBlock;

}
