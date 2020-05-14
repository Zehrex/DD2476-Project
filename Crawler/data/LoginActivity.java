14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_login/src/main/java/com/hl/modules_login/view/LoginActivity.java
package com.hl.modules_login.view;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hl.base_module.appcomponent.UserManager;
import com.hl.base_module.constant.ArouterPath;
import com.hl.base_module.constant.HomePath;
import com.hl.base_module.message.MessageEvent;
import com.hl.base_module.page.BaseWithServiceActivity;
import com.hl.base_module.util.edittext.TextInputEditTextWatcher;
import com.hl.base_module.util.screen.ScreenUtil;
import com.hl.base_module.util.app.ToastUtil;
import com.hl.base_module.viewmodel.SelfViewModelFactory;
import com.hl.modules_login.R;
import com.hl.modules_login.databinding.ActivityLoginBinding;
import com.hl.modules_login.view.event.LoginEventHandler;
import com.hl.modules_login.model.bean.UserBean;
import com.hl.modules_login.viewmodel.UserViewModel;

import org.greenrobot.eventbus.EventBus;

// 替换为Navigation方式，一个界面多个碎片切换，方便注册，登录等跳转
//@Route(path = ArouterPath.LOGIN_ACTIVITY)
public class LoginActivity extends BaseWithServiceActivity<ActivityLoginBinding> {
    @Autowired
    public String from;

    private ActivityLoginBinding activityLoginBinding;
    public UserViewModel userViewModel;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean bIsLightThenDark() {
        // 登录白色背景需要配暗色状态栏
        return false;
    }

    @Override
    public void initLayout(Context context) {
        activityLoginBinding = getViewDataBinding();
        // 设置距离标题栏的高度(系统默认标题栏高度为56dp)
        ScreenUtil.setMargin(activityLoginBinding.alTopCard,
                -10000, ScreenUtil.STATUS_BAR_HEIGHT * 2,
                -10000, -10000);

        activityLoginBinding.alUserNameEt.setText("小坑神周杰伦");
        activityLoginBinding.alUserPassEt.setText("988815xy");
        ToastUtil.showTost(from);
    }

    @Override
    public void requestData(Context context) {
        // 自定义ModelFactory创建ViewModel
        userViewModel = new ViewModelProvider(this, new SelfViewModelFactory(baseControlPresenter)).get(UserViewModel.class);
        // 监听Live数据变化
        userViewModel.getUserLiveData().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {
                // 0.保存用户信息
                UserManager.saveUser(userBean.getUsername());

                // 1. 直接返回结果到跳转页面
                Intent intent = new Intent();
                intent.putExtra("user", "登录成功了，我叫" + userBean.getUsername());
                setResult(RESULT_OK, intent);

                // 2. 用Eventbus通知其他页面
                EventBus.getDefault().post(new MessageEvent(userBean.getUsername()));

                // 3. 路由到主页面，然后切换到某个碎片，实现跨页面跳转
                ARouter.getInstance()
                        .build(ArouterPath.HOME_ACTIVITY)
                        .withInt(HomePath.WHICH, HomePath.HOME_PAGE)
                        .navigation();

                finish();
            }
        });
    }

    @Override
    public void eventHandler(Context context) {
        activityLoginBinding.alUserNameEt.addTextChangedListener(new TextInputEditTextWatcher(activityLoginBinding.alUserNameTL));
        activityLoginBinding.alUserPassEt.addTextChangedListener(new TextInputEditTextWatcher(activityLoginBinding.alUserPassTL));

        // 注册事件对象
        if (null == activityLoginBinding.getLoginHandler()) {
            activityLoginBinding.setLoginHandler(new LoginEventHandler(this));
        }
    }

    @Override
    public void onSucess(String _functionName, Object t) {
        if (t instanceof UserBean) {
            UserBean result = (UserBean) t;
            userViewModel.getUserLiveData().setValue(result);
        }
    }

    @Override
    public void onFailed(String _functionName, String _message) {

    }
}
