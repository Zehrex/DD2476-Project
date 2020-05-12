2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/utils/ConfirmDialog.java
package com.mediaroom.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mediaroom.R;


/**
 * @author zhaochong
 * @desc TODO
 * @date on 2019-07-22
 * @email zoro.zhaochong@gmail.com
 */

public class ConfirmDialog extends BaseDialog {

    private final TextView tvTitle;
    private final TextView tvCancel;
    private final TextView tvOk;
    private final TextView tvDesc;
    private final View vLine;
    private final View vHorLine;

    public interface OnConfirmCallback {
        void onSure();

        void onCancel();
    }

    public ConfirmDialog(Context context, final OnConfirmCallback listener) {
        super(context);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvOk = (TextView) view.findViewById(R.id.tv_ok);
        tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        vLine = view.findViewById(R.id.v_confirm_line);
        vHorLine = view.findViewById(R.id.v_confirm_hline);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancel();
                }
                dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSure();
                }
                dismiss();
            }
        });
    }

    public void setTitle(CharSequence title) {
        this.tvTitle.setText(title);
    }

    public void setTitleTextSize(float dp) {
        this.tvTitle.setTextSize(dp);
    }

    public ConfirmDialog setDesc(CharSequence desc) {
        if (!TextUtils.isEmpty(desc)) {
            this.tvDesc.setVisibility(View.VISIBLE);
            this.tvDesc.setText(desc);
        } else {
            this.tvDesc.setVisibility(View.GONE);
        }
        return this;
    }

    public ConfirmDialog setDescTextColor(int color) {
        this.tvDesc.setTextColor(color);
        return this;
    }

    public void setDescTextSize(float dp) {
        this.tvDesc.setTextSize(dp);
    }

    public void setButton(CharSequence cancelCs, CharSequence okCs) {
        tvCancel.setText(cancelCs);
        tvOk.setText(okCs);
    }

    public void setButton(CharSequence okCs) {
        tvCancel.setVisibility(View.GONE);
        vLine.setVisibility(View.GONE);
        vHorLine.setVisibility(View.VISIBLE);
        tvOk.setText(okCs);
    }

    public void setTitleGrivate() {
        tvTitle.setGravity(Gravity.LEFT);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_confirm;
    }
}
