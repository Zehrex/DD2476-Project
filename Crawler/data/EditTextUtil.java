2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/utils/EditTextUtil.java
package com.mediaroom.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author zhaochong
 * @desc TODO
 * @date on 2019-06-04
 * @email zoro.zhaochong@gmail.com
 */

public class EditTextUtil {


    /**
     * 获取对象
     *
     * @param mLocalUIdEt
     * @param mBtn
     */
    public static void checkUidEditTextChanged(EditText mLocalUIdEt, Button mBtn) {
        mLocalUIdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                int len = editable.toString().length();
                if (text.startsWith("0")) {//首数字不可为0
//                    editable.replace(0, 1, "");
                    editable.clear();
                }
                if (len > 0 && !text.startsWith("0")) {
                    mBtn.setEnabled(true);
                } else {
                    mBtn.setEnabled(false);
                }
            }
        });
    }

    public static void checkRoomIdEditTextChanged(EditText mLocalRoomIdEt, Button mBtn) {
        mLocalRoomIdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int len = editable.toString().length();
                if (len > 0) {
                    mBtn.setEnabled(true);
                } else {
                    mBtn.setEnabled(false);
                }
            }
        });
    }
}

