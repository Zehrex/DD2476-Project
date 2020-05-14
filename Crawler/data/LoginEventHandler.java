14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_login/src/main/java/com/hl/modules_login/view/event/LoginEventHandler.java
package com.hl.modules_login.view.event;

import android.text.TextUtils;
import android.view.View;

import com.hl.base_module.handler.BaseHandlers;
import com.hl.modules_login.view.LoginActivity;
import com.hl.modules_login.databinding.ActivityLoginBinding;

/**
 * 登录相关事件
*@Author: hl
*@Date: created at 2020/3/28 9:52
*@Description: com.hl.modules_login.view.event
*/
public class LoginEventHandler extends BaseHandlers<LoginActivity, ActivityLoginBinding> {

    public LoginEventHandler(LoginActivity loginActivity) {
        super(loginActivity, loginActivity.getViewDataBinding());
    }

    /**
     * 点击登录
     * @param view
     */
    public void clickLogin(View view){
        // 获取登录后的数据，然后跳转
        if (TextUtils.isEmpty(getBindingView().alUserNameEt.getText())) {
            getBindingView().alUserNameTL.setError("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(getBindingView().alUserPassEt.getText())) {
            getBindingView().alUserPassTL.setError("请输入密码");
            return;
        }
        getBindedView().userViewModel.login(
                getBindingView().alUserNameEt.getText().toString(),
                getBindingView().alUserPassEt.getText().toString());
    }
}
