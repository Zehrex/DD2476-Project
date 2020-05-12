2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-proxy_cache/src/main/java/com/danikula/videocache/file/UnlimitedDiskUsage.java
package com.danikula.videocache.file;

import java.io.File;
import java.io.IOException;

/**
 * Unlimited version of {@link DiskUsage}.
 *
 * @author Alexey Danilov (danikula@gmail.com).
 */
public class UnlimitedDiskUsage implements DiskUsage {

    @Override
    public void touch(File file) throws IOException {
        // do nothing
    }
}
