1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/PasswordForgetActivity.java
package com.hjq.demo.ui.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.http.model.HttpData;
import com.hjq.demo.http.request.GetCodeApi;
import com.hjq.demo.http.request.RefoundApi;
import com.hjq.demo.http.request.VerifyCodeApi;
import com.hjq.demo.http.response.GetCode;
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
 * time   : 2019/02/27
 * desc   : 忘记密码
 */
public final class PasswordForgetActivity extends MyActivity {
    @BindView(R.id.toLogin)
    TextView toLogin;
    @BindView(R.id.mobileEdit)
    SeniorEditText mPhoneView;

    @BindView(R.id.testCodeEdit)
    SeniorEditText mCodeView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.pwdEdit)
    SeniorEditText pwdEdit;
    @BindView(R.id.repeatPwdEdit)
    SeniorEditText repeatPwdEdit;
    @BindView(R.id.resetBtn)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.resetpwd_activity;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(pwdEdit)
                .addView(mCodeView)
                .addView(repeatPwdEdit)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11)
                .build();

        setOnClickListener(R.id.resetBtn, R.id.toLogin, R.id.cv_register_countdown);
        mPhoneView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String code = mCodeView.getText().toString().trim();
                String pwd = pwdEdit.getText().toString().trim();
                String repeatpwd = repeatPwdEdit.getText().toString().trim();
                if (s.toString().length() > 0 && code.length() > 0 && pwd.length() > 0 && repeatpwd.length() > 0) {
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
                String moblie = mPhoneView.getText().toString().trim();
                String code = mCodeView.getText().toString().trim();
                String repeatpwd = repeatPwdEdit.getText().toString().trim();
                if (s.toString().length() > 0 && moblie.length() > 0 && code.length() > 0 && repeatpwd.length() > 0) {
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
                String moblie = mPhoneView.getText().toString().trim();
                String pwd = pwdEdit.getText().toString().trim();
                String repeatpwd = repeatPwdEdit.getText().toString().trim();
                if (s.toString().length() > 0 && moblie.length() > 0 && pwd.length() > 0 && repeatpwd.length() > 0) {
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
        repeatPwdEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String moblie = mPhoneView.getText().toString().trim();
                String pwd = pwdEdit.getText().toString().trim();
                String code = mCodeView.getText().toString().trim();
                if (s.toString().length() > 0 && moblie.length() > 0 && pwd.length() > 0 && code.length() > 0) {
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

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                // 验证码校验
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }
                // 获取验证码
                EasyHttp.post(this)
                        .api(new GetCodeApi()
                                .setPhone(mPhoneView.getText().toString())
                                .setType("2"))
                        .request(new HttpCallback<HttpData<GetCode>>(this) {

                            @Override
                            public void onSucceed(HttpData<GetCode> getCode) {
                                if (getCode != null) {
                                    int code = getCode.getCode();
                                    String message = getCode.getMessage();
                                    if (code == 0) {
                                        toast(R.string.common_code_send_hint);
                                        mCountdownView.start();
                                    }
                                    if (!TextUtils.isEmpty(message)) {
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
            case R.id.resetBtn:
                //重置密码
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }
                // 判断新密码输入是否一致
                String pwd = pwdEdit.getText().toString().trim();
                String repeatpwd = repeatPwdEdit.getText().toString().trim();
                if (pwd.equals(repeatpwd)){
                    toast("密码输入不一致");
                    return;
                }
                EasyHttp.post(this)
                        .api(new RefoundApi()
                                .setAccount(mPhoneView.getText().toString().trim())
                                .setCode(mCodeView.getText().toString().trim())
                                .setNewPwd(Md5Util.md5(pwdEdit.getText().toString().trim()))
                                .setRepeatNewPwd(Md5Util.md5(repeatPwdEdit.getText().toString().trim())))
                        .request(new HttpCallback<HttpData<Void>>(this) {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
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
}