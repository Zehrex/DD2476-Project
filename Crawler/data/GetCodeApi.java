1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/request/GetCodeApi.java
package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 获取验证码
 */
public class GetCodeApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/msg";
    }

    /** 手机号 */
    private String phone;
    /** 1 注册 2 密码找回 */
    private String type;

    public GetCodeApi setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public GetCodeApi setType(String type) {
        this.type = type;
        return this;
    }
}