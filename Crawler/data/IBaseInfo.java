9
https://raw.githubusercontent.com/TrillGates/TaobaoUnion/master/app/src/main/java/com/sunofbeaches/taobaounion/model/domain/IBaseInfo.java
package com.sunofbeaches.taobaounion.model.domain;

public interface IBaseInfo {
    /**
     * 商品的封面
     *
     * @return
     */
    String getCover();

    /**
     * 商品的标题
     *
     * @return
     */
    String getTitle();

    /**
     * 商品的Url
     *
     * @return
     */
    String getUrl();
}
