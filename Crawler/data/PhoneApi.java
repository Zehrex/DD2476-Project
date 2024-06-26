1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/request/PhoneApi.java
package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 修改手机
 */
public class PhoneApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/phone";
    }

    /** 旧手机号验证码（没有绑定情况下可不传） */
    private String preCode;

    /** 新手机号 */
    private String phone;
    /** 新手机号验证码 */
    private String code;

    public PhoneApi setPreCode(String preCode) {
        this.preCode = preCode;
        return this;
    }

    public PhoneApi setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PhoneApi setCode(String code) {
        this.code = code;
        return this;
    }
}