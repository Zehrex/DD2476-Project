1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/request/RefoundApi.java
package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : zhangpeisen
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 密码找回
 */
public class RefoundApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/refound";
    }

    /** 手机号/邮箱 string */
    private String account;
    /** 新密码 string */
    private String pwd_new;
    /** 确认新密码 string */
    private String pwd_new_repeat;
    /** 验证码 string */
    private String code;

    public RefoundApi setAccount(String account) {
        this.account = account;
        return this;
    }
    public RefoundApi setNewPwd(String pwd_new) {
        this.pwd_new = pwd_new;
        return this;
    }
    public RefoundApi setRepeatNewPwd(String pwd_new_repeat) {
        this.account = pwd_new_repeat;
        return this;
    }
    public RefoundApi setCode(String code) {
        this.code = code;
        return this;
    }
}