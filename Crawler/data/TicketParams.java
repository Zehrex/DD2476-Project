9
https://raw.githubusercontent.com/TrillGates/TaobaoUnion/master/app/src/main/java/com/sunofbeaches/taobaounion/model/domain/TicketParams.java
package com.sunofbeaches.taobaounion.model.domain;

public class TicketParams {
    private String url;
    private String title;

    public TicketParams(String url,String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
