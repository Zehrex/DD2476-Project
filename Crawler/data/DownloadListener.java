1
https://raw.githubusercontent.com/pengfeigao/AgoraCallApi/master/call-plugin-api/src/main/java/com/basetools/net/DownloadListener.java
package com.basetools.net;

import java.io.File;

public interface DownloadListener {
    void onFinish(File file);

    void onProgress(int progress);

    void onFailed(String errMsg);
}