2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/data/HttpItemCallback.java
package com.mediaroom.data;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * Http callback process
 *
 * ZH:
 * Http回调处理
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/18
 */
public abstract class HttpItemCallback<T> implements Callback {
    @Override
    public void onFailure(@NonNull Call call, IOException e) {
        onFail(new BaseError());
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body == null) {
                onFail(new BaseError());
            } else {
                String value = body.string();
                ResponeModel<T> responeModel = JSON.parseObject(value,
                        new TypeReference<ResponeModel<T>>() {
                        });
                onSuccess(responeModel.getObject());
            }
        } else {
            onFail(new BaseError(response.code(), response.message()));
        }
    }

    public abstract void onSuccess(T data);

    public abstract void onFail(BaseError error);
}
