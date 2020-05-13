1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/LoginActivity.java
package com.hjq.demo.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.hjq.demo.R;
import com.hjq.demo.aop.DebugLog;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.http.model.HttpData;
import com.hjq.demo.http.request.LoginApi;
import com.hjq.demo.http.response.LoginBean;
import com.hjq.demo.other.IntentKey;
import com.hjq.demo.other.KeyboardWatcher;
import com.hjq.demo.util.Md5Util;
import com.hjq.demo.util.RsaEncryptUtils;
import com.hjq.demo.widget.SeniorEditText;
import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;

import java.security.KeyPair;
import java.security.PrivateKey;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 登录界面
 */
public final class LoginActivity extends MyActivity
        implements KeyboardWatcher.SoftKeyboardStateListener {

    @DebugLog
    public static void start(Context context, String phone, String password) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(IntentKey.PHONE, phone);
        intent.putExtra(IntentKey.PASSWORD, password);
        context.startActivity(intent);
    }

    @BindView(R.id.iv_login_logo)
    ImageView mLogoView;

    @BindView(R.id.ll_login_body)
    LinearLayout mBodyLayout;
    @BindView(R.id.mobileEdit)
    SeniorEditText mPhoneView;
    @BindView(R.id.pwdEdit)
    SeniorEditText mPasswordView;

    @BindView(R.id.loginBtn)
    AppCompatButton mCommitView;

    @BindView(R.id.v_login_blank)
    View mBlankView;

    /**
     * logo 缩放比例
     */
    private final float mLogoScale = 0.8f;
    /**
     * 动画时间
     */
    private final int mAnimTime = 300;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mPasswordView)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 &&
                        mPasswordView.getText().toString().length() >= 6)
                .build();
        mPhoneView.setInputType(InputType.TYPE_CLASS_PHONE | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);//电话和邮箱
        setOnClickListener(R.id.forgetpwd, R.id.loginBtn, R.id.toRegister);
        mPhoneView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pwd = mPasswordView.getText().toString().trim();
                if (s.toString().length() > 0 && pwd.length() > 0) {
                    mCommitView.setBackgroundResource(R.drawable.login_bg);
                    mCommitView.setTextColor(Color.WHITE);
                    mCommitView.setEnabled(true);
                } else {
                    mCommitView.setBackgroundResource(R.drawable.normal_bg);
                    mCommitView.setTextColor(Color.WHITE);
                    mCommitView.setEnabled(false);
                }
            }
        });
        mPasswordView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String moblie = mPhoneView.getText().toString().trim();
                if (s.toString().length() > 0 && moblie.length() > 0) {
                    mCommitView.setBackgroundResource(R.drawable.login_bg);
                    mCommitView.setTextColor(Color.WHITE);
                    mCommitView.setEnabled(true);
                } else {
                    mCommitView.setBackgroundResource(R.drawable.normal_bg);
                    mCommitView.setTextColor(Color.WHITE);
                    mCommitView.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void initData() {

        postDelayed(() -> {
            // 因为在小屏幕手机上面，因为计算规则的因素会导致动画效果特别夸张，所以不在小屏幕手机上面展示这个动画效果
            if (mBlankView.getHeight() > mBodyLayout.getHeight()) {
                // 只有空白区域的高度大于登录框区域的高度才展示动画
                KeyboardWatcher.with(LoginActivity.this)
                        .setListener(LoginActivity.this);
            }
        }, 500);

        // 填充传入的手机号和密码
        mPhoneView.setText(getString(IntentKey.PHONE));
        mPasswordView.setText(getString(IntentKey.PASSWORD));
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgetpwd:
                startActivity(PasswordForgetActivity.class);
                break;
            case R.id.loginBtn:
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }
                // 获取ras私钥
                KeyPair keyPair = RsaEncryptUtils.createRsaPassword();
                PrivateKey aPrivate = keyPair.getPrivate();
                //用 rsa 加密的账号+密码
                String content = mPhoneView.getText().toString().trim() + mPasswordView.getText().toString().trim();
                String encoderesult = RsaEncryptUtils.rsaEncode(content, aPrivate);
                EasyHttp.post(this)
                        .api(new LoginApi()
                                .setAccount(mPhoneView.getText().toString())
                                .setPwd(Md5Util.md5(mPasswordView.getText().toString()))
                                .setFingerPrint(encoderesult))
                        .request(new HttpCallback<HttpData<LoginBean>>(this) {

                            @Override
                            public void onSucceed(HttpData<LoginBean> data) {
                                // 更新 Token
                                EasyConfig.getInstance()
                                        .addParam("token", data.getData().getToken());
                                // 跳转到主页
                                startActivity(HomeActivity.class);
                                finish();
                            }

                            @Override
                            public void onFail(Exception e) {
                                super.onFail(e);
                                toast(e.getMessage());
                            }
                        });
                break;
            case R.id.toRegister:
                // 跳转到注册界面
                startActivityForResult(RegisterActivity.class, (resultCode, data) -> {
                    // 如果已经注册成功，就执行登录操作
                    if (resultCode == RESULT_OK && data != null) {
                        mPhoneView.setText(data.getStringExtra(IntentKey.PHONE));
                        mPasswordView.setText(data.getStringExtra(IntentKey.PASSWORD));
                        onClick(mCommitView);
                    }
                });
                break;
//            case R.id.iv_login_wx:
//                toast("记得改好第三方 AppID 和 AppKey，否则会调不起来哦");
//                Platform platform;
//                switch (v.getId()) {
//                    case R.id.iv_login_qq:
//                        platform = Platform.QQ;
//                        break;
//                    case R.id.iv_login_wx:
//                        platform = Platform.WECHAT;
//                        toast("也别忘了改微信 " + WXEntryActivity.class.getSimpleName() + " 类所在的包名哦");
//                        break;
//                    default:
//                        throw new IllegalStateException("are you ok?");
//                }
//                UmengClient.login(this, platform, this);
//                break;
            default:
                break;
        }
    }

    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */

    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int[] location = new int[2];
        // 获取这个 View 在屏幕中的坐标（左上角）
        mBodyLayout.getLocationOnScreen(location);
        //int x = location[0];
        int y = location[1];
        int bottom = screenHeight - (y + mBodyLayout.getHeight());
        if (keyboardHeight > bottom) {
            // 执行位移动画
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBodyLayout, "translationY", 0, -(keyboardHeight - bottom));
            objectAnimator.setDuration(mAnimTime);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.start();

            // 执行缩小动画
            mLogoView.setPivotX(mLogoView.getWidth() / 2f);
            mLogoView.setPivotY(mLogoView.getHeight());
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogoView, "scaleX", 1.0f, mLogoScale);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogoView, "scaleY", 1.0f, mLogoScale);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mLogoView, "translationY", 0.0f, -(keyboardHeight - bottom));
            animatorSet.play(translationY).with(scaleX).with(scaleY);
            animatorSet.setDuration(mAnimTime);
            animatorSet.start();
        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBodyLayout, "translationY", mBodyLayout.getTranslationY(), 0);
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        if (mLogoView.getTranslationY() == 0) {
            return;
        }
        // 执行放大动画
        mLogoView.setPivotX(mLogoView.getWidth() / 2f);
        mLogoView.setPivotY(mLogoView.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogoView, "scaleX", mLogoScale, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogoView, "scaleY", mLogoScale, 1.0f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mLogoView, "translationY", mLogoView.getTranslationY(), 0);
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}