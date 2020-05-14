14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_common/src/main/java/com/hl/base_module/page/BaseWithServiceActivity.java
package com.hl.base_module.page;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hl.base_module.R;
import com.hl.base_module.page.observer.ActivityObserver;
import com.hl.base_module.util.app.StatusBarUtil;
import com.hl.base_module.util.screen.ScreenUtil;
import com.hl.base_module.util.time.TimeUtil;
import com.hl.base_module.util.app.ToastUtil;
import com.hl.lib_network.controller.BaseControlContract;
import com.hl.lib_network.controller.presenter.BaseControlPresenter;

/**
 * 基础Activity页面 + 初始化了请求服务
 */
public abstract class BaseWithServiceActivity<T extends ViewDataBinding> extends AppCompatActivity implements BaseControlContract.View {
    /**
     * 请求服务
     */
    public BaseControlPresenter baseControlPresenter = null;

    public String TAG = getClass().getName();
    private T viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 0. Inflate the layout for this AppCompatActivity
        setContentView(R.layout.activity_base);
        // 0.1 沉浸式走起
        if (bIsLightThenDark()) {
            StatusBarUtil.initWhiteLight(this);
        } else {
            StatusBarUtil.initBlackLight(this);
        }
        // 0.5 设置标题
        String title = setTitle();
        if (TextUtils.isEmpty(title)){
            findViewById(R.id.ab_titleRoot).setVisibility(View.GONE);
        } else {
            // 沉浸式处理
            ScreenUtil.setStatusBarHeightTop(findViewById(R.id.ab_titleRoot));
            ((TextView)findViewById(R.id.ab_titleTv)).setText(title);
        }
        // 1.TODO 标题相关操作、沉浸式、打开分享按钮等
        // 2.Use DataBinding创建内部布局，添加内容布局
        viewDataBinding = DataBindingUtil.inflate(getLayoutInflater(), setLayout(), null, false);
        // 2.1 设置统一背景，方便切换夜间模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewDataBinding.getRoot().setBackgroundColor(getResources().getColor(R.color.page_bg_color, null));
        } else {
            viewDataBinding.getRoot().setBackgroundColor(getResources().getColor(R.color.page_bg_color));
        }
        // 3.添加内容布局
        FrameLayout ab_contentRoot = findViewById(R.id.ab_contentRoot);
        ab_contentRoot.addView(viewDataBinding.getRoot());
        // 3. 统一注解Arouter
        ARouter.getInstance().inject(this);
        // 初始化View、适配器等调用
        initLayout(this);
        // 由基础页面创建请求服务，管理生命周期
        baseControlPresenter = new BaseControlPresenter(this);
        // 数据请求调用
        requestData(this);
        // 事件绑定
        eventHandler(this);
        // 注册生命周期监听 - 然后做一些绑定、解绑、埋点等操作
        getLifecycle().addObserver(new ActivityObserver(this, getLifecycle()));
    }

    /**
     * 防重复点击事件
     * @param view
     * @param listener
     */
    @BindingAdapter("android:onClick")
    public static void onClick(final View view, final View.OnClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TimeUtil.isFastDoubleClick()) {
                    if (null != listener) {
                        listener.onClick(view);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != baseControlPresenter) {
            baseControlPresenter.releaseResource();
            baseControlPresenter = null;
        }
    }

    /**
     * 获取对应的DataBinding注解对象
     *
     * @return
     */
    public T getViewDataBinding() {
        if (null == viewDataBinding) {
            throw new NullPointerException("Not init viewDataBinding!");
        }
        return viewDataBinding;
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void retryDialog() {

    }

    @Override
    public void emptyDialog() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showTost(msg);
    }

    @Override
    public void onLoinOut(String _functionName, Object object) {

    }

    @Override
    public void onThirdBind(String _functionName, int code) {

    }

    /**
     * 设置标题
     * @param mTitle
     */
    public void setBarTitle(String mTitle) {
        ((TextView)findViewById(R.id.ab_titleTv)).setText(mTitle);
    }

    protected boolean bIsLightThenDark(){             // 设置系统状态栏颜色是亮色还是暗色
        return true;
    }
    protected String setTitle(){                       // 设置标题
        return "";
    }

    public abstract int setLayout();                   // 设置布局

    public abstract void initLayout(Context context);  // 动态调整布局控件大小、间距、初始适配器等

    public abstract void requestData(Context context);  // 发起数据请求

    public abstract void eventHandler(Context context);  // 事件处理
    //.....
}