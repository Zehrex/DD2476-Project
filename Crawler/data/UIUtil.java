1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/view/magicindicator/buildins/UIUtil.java
/**
 *  _                _
 * | |              (_)
 * | | ___   _  __ _ _ _   _  __ _ _ __
 * | |/ / | | |/ _` | | | | |/ _` | '_ \
 * |   <| |_| | (_| | | |_| | (_| | | | |
 * |_|\_\\__,_|\__,_|_|\__, |\__,_|_| |_|
 *                      __/ |
 *                     |___/
 */

package com.hjq.demo.view.magicindicator.buildins;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public final class UIUtil {

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static Resources getResource(Context context) {
        return context.getResources();
    }


    public static int getColor(Context context,int id) {
        return getResource(context).getColor(id);
    }

    public static int getDimens(Context context,int id){
        return getResource(context).getDimensionPixelOffset(id);
    }

    public static void showToast(Context context,String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showToast(Context context,int str_id) {
        Toast toast = Toast.makeText(context, str_id, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}