1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/impl/PromoServiceImpl.java
package com.loststars.quickbuy.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loststars.quickbuy.dao.PromoDOMapper;
import com.loststars.quickbuy.dataobject.PromoDO;
import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.error.EmBusinessError;
import com.loststars.quickbuy.service.PromoService;
import com.loststars.quickbuy.service.model.PromoModel;

@Service
public class PromoServiceImpl implements PromoService {
    
    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) throws BusinessException {
        if (itemId == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        if (promoDO == null) return null;
        PromoModel promoModel = convertFromDO(promoDO);
        return promoModel;
    }
    
    private PromoModel convertFromDO(PromoDO promoDO) {
        if (promoDO == null) return null;
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        DateTime nowDate = new DateTime();
        if (nowDate.isAfter(promoModel.getEndDate())) {
            promoModel.setStatus(PromoModel.STATUS_END);
        } else if (nowDate.isAfter(promoModel.getStartDate())) {
            promoModel.setStatus(PromoModel.STATUS_NOW);
        } else {
            promoModel.setStatus(PromoModel.STATUS_WAIT);
        }
        return promoModel;
    }
}
