24
https://raw.githubusercontent.com/giswangsj/RvParallaxImageView/master/lib/src/main/java/per/wsj/lib/controller/IController.java
package per.wsj.lib.controller;

import android.graphics.Bitmap;

import per.wsj.lib.ProcessCallback;


public interface IController {
    void process(int viewWidth);

    Bitmap getTargetBitmap();

    void setProcessCallback(ProcessCallback callback);
}
