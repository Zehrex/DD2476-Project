1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/request/RegisterApi.java
package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : Android zhangpeisen
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 用户注册
 */
public class RegisterApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/register";
    }

    /** 手机号/邮箱 string */
    private String account;
    /** 密码 string */
    private String pwd;
    /** 短信验证码 string */
    private String code;
    /** 邀请码 string */
    private String inviteCode;
    /** 邀请码 string */
    private String from;

    public RegisterApi setAccount(String account) {
        this.account = account;
        return this;
    }

    public RegisterApi setCode(String code) {
        this.code = code;
        return this;
    }

    public RegisterApi setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public RegisterApi setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
        return this;
    }

    public RegisterApi setFrom(String from) {
        this.from = from;
        return this;
    }
}