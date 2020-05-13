2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/ui/MyButton.java
package com.mediaroom.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.mediaroom.R;

/**
 *
 * Custom button with some state
 *
 * ZH:
 * 自定义按钮，增加一些状态
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/23
 */
public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * The status of Unavailable
     *
     * ZH:
     * 不可用状态
     */
    public void showDisableStatus() {
        setEnabled(false);
        setBackgroundResource(R.drawable.shape_button_background_disable);
        setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_disable));
    }

    /**
     * THe status of Disabled
     *
     * ZH:
     * 关闭状态
     */
    public void showCloseStatus() {
        setEnabled(true);
        setBackgroundResource(R.drawable.shape_button_background_disable);
        setTextColor(ContextCompat.getColor(getContext(), R.color.button_background));
    }

    /**
     * open status
     *
     * ZH:
     * 打开状态
     */
    public void showOpenStatus() {
        setEnabled(true);
        setBackgroundResource(R.drawable.shape_button_background_enable);
        setTextColor(Color.WHITE);
    }
}
