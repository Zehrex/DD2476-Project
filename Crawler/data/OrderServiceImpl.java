1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/impl/OrderServiceImpl.java
package com.loststars.quickbuy.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.loststars.quickbuy.dao.OrderDOMapper;
import com.loststars.quickbuy.dao.SequenceDOMapper;
import com.loststars.quickbuy.dataobject.OrderDO;
import com.loststars.quickbuy.dataobject.SequenceDO;
import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.error.EmBusinessError;
import com.loststars.quickbuy.service.ItemService;
import com.loststars.quickbuy.service.OrderService;
import com.loststars.quickbuy.service.UserService;
import com.loststars.quickbuy.service.model.ItemModel;
import com.loststars.quickbuy.service.model.OrderModel;
import com.loststars.quickbuy.service.model.PromoModel;
import com.loststars.quickbuy.service.model.UserModel;
import com.loststars.quickbuy.util.SpringApplicationContextUtil;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private SequenceDOMapper sequenceDOMapper;
    
    @Autowired
    private OrderDOMapper orderDOMapper;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        if (userId == null || itemId == null || amount == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        if (amount <= 0 || amount > 99) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        if (promoId != null && itemModel.getPromoModel() != null && itemModel.getPromoModel().getStatus().intValue() == PromoModel.STATUS_NOW) {
            if (promoId.intValue() != itemModel.getPromoModel().getId().intValue()) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setId(createOrderId());
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(orderModel.getAmount())));
        orderDOMapper.insertSelective(convertFromModel(orderModel));
        itemService.decreaseStock(itemId, amount);
        itemService.increaseSales(itemId, amount);
        return orderModel;
    }
    
    private OrderDO convertFromModel(OrderModel orderModel) {
        if (orderModel == null) return null;
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }
    
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String createOrderId() throws BusinessException {
        String id = null;
        StringBuilder builder = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        builder.append(date);
        SequenceDO sequenceDO = sequenceDOMapper.selectByPrimaryKey(SequenceDO.ID_NAME_ORDER);
        if (sequenceDO == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        String sequence = String.valueOf(sequenceDO.getCurrentValue());
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        for (int i = 0; i < 6 - sequence.length(); ++ i) {
            builder.append(0);
        }
        builder.append(sequence);
        builder.append("00");
        id = builder.toString();
        return id;
    }
    
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void a() throws Exception {
        OrderDO orderDO = new OrderDO();
        orderDO.setId("0005");
        orderDOMapper.insertSelective(orderDO);
        SpringApplicationContextUtil.getApplicationContext().getBean(this.getClass()).b();
        boolean e = false;
        if (e)
            throw new Exception();
    }
    
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void b() throws Exception {
        OrderDO orderDO = new OrderDO();
        orderDO.setId("0006");
        orderDOMapper.insertSelective(orderDO);
        boolean e = true;
        if (e)
            throw new Exception();
    }

}
