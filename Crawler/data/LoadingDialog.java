2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/utils/LoadingDialog.java
package com.mediaroom.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mediaroom.R;

//1、Create LoadingDialog to inherit Dialog and implement constructor
//1,创建LoadingDialog继承Dialog并实现构造方法
public class LoadingDialog extends Dialog {

  public LoadingDialog(@NonNull Context context) {
    super(context);
  }

  public LoadingDialog(@NonNull Context context, int themeResId) {
    super(context, themeResId);
  }

  protected LoadingDialog(@NonNull Context context, boolean cancelable,
                          @Nullable OnCancelListener cancelListener) {
    super(context, cancelable, cancelListener);
  }

  //2、Create a static inner class Builder, encapsulate some properties of dialog into this class
  //2,创建静态内部类Builder，将dialog的部分属性封装进该类
  public static class Builder {

    private Context context;
    //Prompt message
    //提示信息
    private String message;
    //Whether to display prompt information
    //是否展示提示信息
    private boolean isShowMessage = true;
    //Whether to cancel when press the back key
    //是否按返回键取消
    private boolean isCancelable = true;
    //Whether to cancel
    //是否取消
    private boolean isCancelOutside = false;

    public Builder(Context context) {
      this.context = context;
    }

    /**
     * Set reminder
     *
     * ZH:
     * 设置提示信息
     */
    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    /**
     * Set whether to display prompt information
     *
     * ZH:
     * 设置是否显示提示信息
     */
    public Builder setShowMessage(boolean isShowMessage) {
      this.isShowMessage = isShowMessage;
      return this;
    }

    /**
     * Set whether you can press the back key to cancel
     *
     * ZH:
     * 设置是否可以按返回键取消
     */
    public Builder setCancelable(boolean isCancelable) {
      this.isCancelable = isCancelable;
      return this;
    }

    /**
     * Whether the setting can be cancelled
     *
     * ZH:
     * 设置是否可以取消
     */
    public Builder setCancelOutside(boolean isCancelOutside) {
      this.isCancelOutside = isCancelOutside;
      return this;
    }

    //Create Dialog
    //创建Dialog
    public LoadingDialog create() {

      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.dialog_loading, null);
      //Set up a dialog with a custom theme
      //设置带自定义主题的dialog
      LoadingDialog loadingDailog = new LoadingDialog(context, R.style.MyDialogStyle);
      TextView msgText = (TextView) view.findViewById(R.id.tipTextView);
      if (isShowMessage) {
        msgText.setText(message);
      } else {
        msgText.setVisibility(View.GONE);
      }
      loadingDailog.setContentView(view);
      loadingDailog.setCancelable(isCancelable);
      loadingDailog.setCanceledOnTouchOutside(isCancelOutside);
      return loadingDailog;
    }
  }

}