1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/RegisterActivity.java
package com.hjq.demo.ui.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.helper.CheckMobileAndEmail;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.http.model.HttpData;
import com.hjq.demo.http.request.GetCodeApi;
import com.hjq.demo.http.request.RegisterApi;
import com.hjq.demo.http.response.GetCode;
import com.hjq.demo.http.response.RegisterBean;
import com.hjq.demo.util.Md5Util;
import com.hjq.demo.widget.SeniorEditText;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.toast.ToastUtils;
import com.hjq.widget.view.CountdownView;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 注册界面
 */
public final class RegisterActivity extends MyActivity {

    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.mobileEdit)
    SeniorEditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.testCodeEdit)
    SeniorEditText mCodeView;

    @BindView(R.id.pwdEdit)
    SeniorEditText pwdEdit;
    @BindView(R.id.invideCodeEdit)
    SeniorEditText invideCodeEdit;

    @BindView(R.id.registerBtn)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.register_activity;
    }

    @Override
    protected void initView() {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(pwdEdit)
                .addView(invideCodeEdit)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 &&
                        pwdEdit.getText().toString().length() >= 6)
                .build();
        mPhoneView.setInputType(InputType.TYPE_CLASS_PHONE | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);//电话和邮箱
        setOnClickListener(R.id.cv_register_countdown, R.id.registerBtn, R.id.toLogin);
        mPhoneView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String testcode = mCodeView.getText().toString().trim();
                String pwdedit = pwdEdit.getText().toString().trim();
                if (s.toString().length() > 0 && testcode.length() > 0 && pwdedit.length() > 0) {
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
        mCodeView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mobile = mPhoneView.getText().toString().trim();
                String pwdedit = pwdEdit.getText().toString().trim();
                if (s.toString().length() > 0 && mobile.length() > 0 && pwdedit.length() > 0) {
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
        pwdEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mobile = mPhoneView.getText().toString().trim();
                String testcode = mCodeView.getText().toString().trim();
                if (s.toString().length() > 0 && testcode.length() > 0 && mobile.length() > 0) {
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

    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        // 不要把整个布局顶上去
        return super.createStatusBarConfig().keyboardEnable(true);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                // 获取验证码
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }
                // 获取验证码
                EasyHttp.post(this)
                        .api(new GetCodeApi()
                                .setPhone(mPhoneView.getText().toString())
                                .setType("1"))
                        .request(new HttpCallback<HttpData<GetCode>>(this) {

                            @Override
                            public void onSucceed(HttpData<GetCode> getCode) {
                                if (getCode!=null){
                                    int code = getCode.getCode();
                                    String message = getCode.getMessage();
                                    if (code==0){
                                        toast(R.string.common_code_send_hint);
                                        mCountdownView.start();
                                    }
                                    if (!TextUtils.isEmpty(message)){
                                        toast(message);
                                    }
                                }
                            }

                            @Override
                            public void onFail(Exception e) {
                                super.onFail(e);
                                toast(e.getMessage());
                            }
                        });
                break;
            case R.id.registerBtn:
                // 提交注册
                EasyHttp.post(this)
                        .api(new RegisterApi()
                                .setAccount(mPhoneView.getText().toString())
                                .setPwd(Md5Util.md5(pwdEdit.getText().toString()))
                                .setCode(mCodeView.getText().toString())
                                .setInviteCode(invideCodeEdit.getText().toString().trim())
                                .setFrom("-1"))
                        .request(new HttpCallback<HttpData<RegisterBean>>(this) {

                            @Override
                            public void onSucceed(HttpData<RegisterBean> data) {
                                LoginActivity.start(getActivity(), mPhoneView.getText().toString(), pwdEdit.getText().toString());
                                setResult(RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFail(Exception e) {
                                super.onFail(e);
                                toast(e.getMessage());
                            }
                        });
                break;
            case R.id.toLogin:
                // 已有账号去登录
                LoginActivity.start(getActivity(), mPhoneView.getText().toString(), pwdEdit.getText().toString());
                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}