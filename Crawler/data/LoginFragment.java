2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/ui/LoginFragment.java
package com.mediaroom.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hummer.im.Error;
import com.hummer.im.HMR;
import com.hummer.im._internals.mq.Source;
import com.hummer.im.service.MQService;
import com.mediaroom.BuildConfig;
import com.mediaroom.R;
import com.mediaroom.utils.BaseFragment;
import com.mediaroom.utils.Constant;
import com.mediaroom.utils.EditTextUtil;
import com.mediaroom.utils.Utils;


public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private EditText etLoginUid;
    private TextView tvAppVersion;
    private Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_login, null);
        etLoginUid = rootView.findViewById(R.id.et_login_uid);
        tvAppVersion = rootView.findViewById(R.id.tv_app_version);
        btnLogin = rootView.findViewById(R.id.btn_login);
        etLoginUid.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        etLoginUid.setText(Constant.uid);
        if (!TextUtils.isEmpty(Constant.uid)) {
            btnLogin.setEnabled(true);
        }
        tvAppVersion.setText(getString(R.string.app_version, BuildConfig.VERSION_NAME));
        EditTextUtil.checkUidEditTextChanged(etLoginUid, btnLogin);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Constant.uid = etLoginUid.getText().toString();
                login(Constant.uid);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(Constant.uid) && Utils.getIsLogin(getActivity())) {
            login(Constant.uid);
        }
    }

    public void login(String uid) {
        showDialogProgress();
        new Thread() {
            @Override
            public void run() {
                super.run();
                byte[] token = Utils.getToken(String.valueOf(uid), String.valueOf(Constant.mAppId), null);
                if (token != null) {
                    HMR.open(Long.parseLong(uid), "cn", null, new String(token), new HMR.Completion() {
                        @Override
                        public void onSuccess() {
                            handler.sendEmptyMessage(0);
                        }

                        @Override
                        public void onFailed(Error err) {
                            handler.sendEmptyMessage(1);
                        }
                    });
                } else {
                    handler.sendEmptyMessage(1);
                }

            }
        }.start();


    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    dissMissDialogProgress();
                    Utils.setUid(getActivity(), Constant.uid);
                    HMR.getService(MQService.class)
                            .addSource(new Source(new Source.Shared(Constant.CHAT_GROUPID, null)));
                    Utils.setIsLogin(getActivity(), true);
                    ((MainActivity) getActivity()).showFragment(MainActivity.SHOW_DEALROOMFRAGMENT);
                    break;
                case 1:
                    dissMissDialogProgress();
//                    if (err.code == Error.Code.NetworkNotFound) {
//                        Toast.makeText(getActivity(), "网络无法连接", Toast.LENGTH_SHORT).show();
//                    } else {
                    Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
//                    }
                    break;
            }
        }
    };
}
