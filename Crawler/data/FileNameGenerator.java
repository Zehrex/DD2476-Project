2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-proxy_cache/src/main/java/com/danikula/videocache/file/FileNameGenerator.java
package com.danikula.videocache.file;

/**
 * Generator for files to be used for caching.
 *
 * @author Alexey Danilov (danikula@gmail.com).
 */
public interface FileNameGenerator {

    String generate(String url);

}
