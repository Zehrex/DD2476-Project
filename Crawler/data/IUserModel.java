14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_login/src/main/java/com/hl/modules_login/model/bean/IUserModel.java
package com.hl.modules_login.model.bean;

public interface IUserModel{
    void getUser(String phone, String pass);
    void registerUser(String phone, String pass, String repass);
}
