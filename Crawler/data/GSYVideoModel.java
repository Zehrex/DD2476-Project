2
https://raw.githubusercontent.com/jarryleo/GSYVideoPlayer/master/gsyVideoPlayer-java/src/main/java/com/shuyu/gsyvideoplayer/model/GSYVideoModel.java
package com.shuyu.gsyvideoplayer.model;

/**
 * Created by shuyu on 2016/12/20.
 */

public class GSYVideoModel {

    private String mUrl;
    private String mTitle;

    public GSYVideoModel(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
