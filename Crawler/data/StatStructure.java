16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/file/linux/StatStructure.java
package com.github.unidbg.file.linux;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

public abstract class StatStructure extends UnicornStructure {

    public StatStructure(Pointer p) {
        super(p);
    }

    public long st_dev;
    public long st_ino;
    public int st_mode;
    public int st_nlink;
    public int st_uid;
    public int st_gid;
    public long st_rdev;
    public long st_size;
    public int st_blksize;
    public long st_blocks;

    public abstract void setLastModification(long lastModified);

}
