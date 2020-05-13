2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/data/IDataSource.java
package com.mediaroom.data;

import java.io.File;

import okhttp3.Callback;

/**
 *
 * Data source interface
 *
 * ZH:
 * 数据源接口
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/18
 */
public interface IDataSource {
    void refreshToken(String appid, String uid, HttpItemCallback<String> callback);

    void feedback(String json, File file, Callback callback);
}
