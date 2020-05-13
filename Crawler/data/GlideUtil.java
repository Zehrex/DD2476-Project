4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/util/GlideUtil.java
package com.sketch.code.two.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sketch.code.two.R;

import java.io.File;

import static com.sketch.code.two.util.Const.BASE_HOST;

public class GlideUtil {
    public static void set(String url, ImageView src, Context context) {
        try {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .dontAnimate();

            Glide.with(context).load(url.concat("&token=").concat(new SketchcodeUtil.User(context).getToken())).apply(requestOptions).into(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void set(int attachmentId, ImageView src, Context context) {
        if (context != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .dontAnimate();

            Glide.with(context)
                    .load(BASE_HOST.concat("attachment/get?id=").concat(String.valueOf(attachmentId)).concat("&token=").concat(new SketchcodeUtil.User(context).getToken()))
                    .apply(requestOptions)
                    .into(src);
        }
    }

    public static void set(File file, ImageView src, Context context)
    {
        if (context != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.mipmap.ic_launcher)
                    .dontAnimate();

            Glide.with(context)
                    .load(file)
                    .apply(requestOptions)
                    .into(src);
        }
    }

}
