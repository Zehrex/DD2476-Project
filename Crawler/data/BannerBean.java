1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/http/response/BannerBean.java
package com.hjq.demo.http.response;

import java.util.List;

/**
 * 轮播bean
 * by 刘坤
 */
public class BannerBean {

    /**
     * Code : 0
     * Msg : 操作成功
     * Data : [{"title":"banner1","link":"http://eva7base.com:96","img_pc":"http://eva7base.com:88/img/banner@2x.png","img_app":"http://eva7base.com:88/img/banner.png"}]
     */

    private int Code;
    private String Msg;
    private List<DataBean> Data;

    @Override
    public String toString() {
        return "BannerBean{" +
                "Code=" + Code +
                ", Msg='" + Msg + '\'' +
                ", Data=" + Data +
                '}';
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * title : banner1
         * link : http://eva7base.com:96
         * img_pc : http://eva7base.com:88/img/banner@2x.png
         * img_app : http://eva7base.com:88/img/banner.png
         */

        private String title;
        private String link;
        private String img_pc;
        private String img_app;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImg_pc() {
            return img_pc;
        }

        public void setImg_pc(String img_pc) {
            this.img_pc = img_pc;
        }

        public String getImg_app() {
            return img_app;
        }

        public void setImg_app(String img_app) {
            this.img_app = img_app;
        }
    }
}