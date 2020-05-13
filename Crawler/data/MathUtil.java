4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/util/MathUtil.java
package com.sketch.code.two.util;

import android.content.Context;

public class MathUtil {
    public static int toDp(int input, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (input * scale + 0.5f);
    }
}
