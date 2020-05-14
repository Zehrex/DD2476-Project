9
https://raw.githubusercontent.com/TrillGates/TaobaoUnion/master/app/src/main/java/com/sunofbeaches/taobaounion/model/domain/ILinearItemInfo.java
package com.sunofbeaches.taobaounion.model.domain;

public interface ILinearItemInfo extends IBaseInfo {

    /**
     * 获取原价
     *
     * @return
     */
    String getFinalPrise();


    /**
     * 获取优惠价格
     *
     * @return
     */
    long getCouponAmount();


    /**
     * 获取销量
     *
     * @return
     */
    long getVolume();

}
