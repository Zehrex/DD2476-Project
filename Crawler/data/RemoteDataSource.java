2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/data/remote/RemoteDataSource.java
package com.mediaroom.data.remote;

import com.mediaroom.data.HttpItemCallback;
import com.mediaroom.data.IDataSource;

import java.io.File;

import okhttp3.Callback;

/**
 *
 * Data Repository
 *
 * ZH：
 * 数据源
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/18
 */
public class RemoteDataSource implements IDataSource {

    @Override
    public void refreshToken(String appid, String uid, HttpItemCallback<String> callback) {

    }

    @Override
    public void feedback(String json, File file, Callback callback) {

    }
}
