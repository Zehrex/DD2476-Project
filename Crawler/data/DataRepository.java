2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/data/DataRepository.java
package com.mediaroom.data;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 *
 * Data Repository
 *
 * ZH:
 * 数据仓库
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/18
 */
public class DataRepository implements IDataSource {

    private static volatile DataRepository instance;
    private Context context;

    private DataRepository(Context context) {
        this.context = context;
    }

    public synchronized static DataRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    @Override
    public void refreshToken(String appid, String uid, HttpItemCallback<String> callback) {
        Map<String, Object> data = new HashMap<>();
        data.put("uid", uid);
        data.put("channelName", "test");
        data.put("validTime", 30);
        data.put("appId", appid);

        RequestBody body =
                RequestBody.create(MediaType.get("application/json;charset=utf-8"),
                        JSON.toJSONString(data));
        Request request = new Request.Builder()
                .url("https://webapi.sunclouds.com/webservice/app/v2/auth/genToken")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }

    @Override
    public void feedback(String json, File file, Callback callback) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("nyy", json)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();
        Request request = new Request.Builder()
                .url("https://isoda-inforeceiver.yy.com/userFeedback")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }
}
