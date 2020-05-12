1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/server/TestServer.java
package com.hjq.demo.http.server;

import com.hjq.http.model.BodyType;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 测试环境
 */
public class TestServer extends ReleaseServer {

    @Override
    public String getHost() { return "http://eva7base.com:99/"; }
//    public String getHost() { return "https://www.baidu.com/"; }


    @Override
    public String getPath() {
        return "";
//        return "api/";
    }


    @Override
    public BodyType getType() {
        return BodyType.JSON;
    }
}