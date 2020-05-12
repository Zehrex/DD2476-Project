2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/base/DataBindBaseActivity.java
package com.mediaroom.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

/**
 *
 * Basic Class
 *
 * ZH：
 * 基础类
 *
 * @author Aslan
 * @date 2018/4/11
 */
public abstract class DataBindBaseActivity<V extends ViewDataBinding> extends BaseActivity {

    protected V mDataBinding;

    @Override
    protected void setMyContentView() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
    }
}