1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/request/LoginApi.java
package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 用户登录
 */
public class LoginApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/login";
    }

    /** 手机号 */
    private String account;
    /** 登录密码 */
    private String pwd;
    /** 用 rsa 加密的账号+密码 */
    private String fingerPrint;


    public LoginApi setAccount(String account) {
        this.account = account;
        return this;
    }
    public LoginApi setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }
    public LoginApi setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
        return this;
    }
}