2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-proxy_cache/src/main/java/com/danikula/videocache/InterruptedProxyCacheException.java
package com.danikula.videocache;

/**
 * Indicates interruption error in work of {@link ProxyCache} fired by user.
 *
 * @author Alexey Danilov
 */
public class InterruptedProxyCacheException extends ProxyCacheException {

    public InterruptedProxyCacheException(String message) {
        super(message);
    }

    public InterruptedProxyCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterruptedProxyCacheException(Throwable cause) {
        super(cause);
    }
}
