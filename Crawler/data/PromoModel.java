1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/model/PromoModel.java
package com.loststars.quickbuy.service.model;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class PromoModel {
    
    public static final int STATUS_NO_PROMO = 0;
    
    public static final int STATUS_WAIT = 1;
    
    public static final int STATUS_NOW = 2;
    
    public static final int STATUS_END = 3;

    private Integer id;
    
    private String name;
    
    private Integer itemId;
    
    private BigDecimal promoItemPrice;
    
    private DateTime startDate;
    
    private DateTime endDate;
    
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
