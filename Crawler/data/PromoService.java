1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/PromoService.java
package com.loststars.quickbuy.service;

import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.service.model.PromoModel;

public interface PromoService {

    public PromoModel getPromoByItemId(Integer itemId) throws BusinessException;
}
