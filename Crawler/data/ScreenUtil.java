2
https://raw.githubusercontent.com/Geekholt/Practice-CustomView/master/app/src/main/java/com/geekholt/practiceui/util/ScreenUtil.java
package com.geekholt.practiceui.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author 吴灏腾
 * @date 2020/5/9
 * @describe TODO
 */
public class ScreenUtil {
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
