14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_login/src/main/java/com/hl/modules_login/viewmodel/UserViewModel.java
package com.hl.modules_login.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hl.anotation.NotProguard;
import com.hl.lib_network.controller.presenter.BaseControlPresenter;
import com.hl.modules_login.model.bean.UserBean;
import com.hl.modules_login.model.respository.UserRepository;

@NotProguard
public class UserViewModel extends ViewModel{
    private MutableLiveData<UserBean> userLiveData;
    private UserRepository userRepository;

    public UserViewModel(BaseControlPresenter baseControlPresenter) {
        userRepository = new UserRepository(baseControlPresenter);
    }

    public MutableLiveData<UserBean> getUserLiveData() {
        if (null == userLiveData) {
            userLiveData = new MutableLiveData<>();
        }
        return userLiveData;
    }

    public void login(String phone, String pass) {
        userRepository.getUser(phone, pass);
    }

    public void register(String phone, String pass, String repass) {
        userRepository.registerUser(phone, pass, repass);
    }
}
