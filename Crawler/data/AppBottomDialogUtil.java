4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/util/AppBottomDialogUtil.java
package com.sketch.code.two.util;

import android.content.Context;

import com.sketch.code.two.R;

import net.neonteam.appbottomdialog.values.Theme;

public class AppBottomDialogUtil {
    public static Theme getThemeSettings (Context context) {
        Theme theme = new Theme();
        theme.rippleBackground = context.getResources().getColor(R.color.colorAccent);
        theme.titleTextColor = context.getResources().getColor(R.color.textColor);
        theme.buttonOkTextColor = context.getResources().getColor(R.color.colorAccent);
        theme.dialogBackground = context.getResources().getColor(R.color.colorPrimary);
        theme.menuItemTextColor = context.getResources().getColor(R.color.textColor);
        theme.defaultButtonTextColor = context.getResources().getColor(R.color.textColor);
        return theme;
    }
}
