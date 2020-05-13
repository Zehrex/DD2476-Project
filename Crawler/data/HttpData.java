1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/model/HttpData.java
package com.hjq.demo.http.model;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 统一接口数据结构
 */
public class HttpData<T> {

    /** 返回码 */
    private int Code;
    /** 提示语 */
    private String Msg;
    /** 数据 */
    private T Data;

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Msg;
    }

    public T getData() {
        return Data;
    }

    @Override
    public String toString() {
        return "HttpData{" +
                "Code=" + Code +
                ", Msg='" + Msg + '\'' +
                ", Data=" + Data +
                '}';
    }
}