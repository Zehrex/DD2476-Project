1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/impl/ItemServiceImpl.java
package com.loststars.quickbuy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loststars.quickbuy.dao.ItemDOMapper;
import com.loststars.quickbuy.dao.ItemStockDOMapper;
import com.loststars.quickbuy.dataobject.ItemDO;
import com.loststars.quickbuy.dataobject.ItemStockDO;
import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.error.EmBusinessError;
import com.loststars.quickbuy.service.ItemService;
import com.loststars.quickbuy.service.PromoService;
import com.loststars.quickbuy.service.model.ItemModel;
import com.loststars.quickbuy.service.model.PromoModel;
import com.loststars.quickbuy.validator.ValidationResult;
import com.loststars.quickbuy.validator.ValidatorImpl;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ValidatorImpl validatorImpl;
    
    @Autowired
    private ItemDOMapper itemDOMapper;
    
    @Autowired
    private ItemStockDOMapper itemStockDOMapper;
    
    @Autowired
    private PromoService promoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        ValidationResult validationResult = validatorImpl.validate(itemModel);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        }
        ItemDO itemDO = convertFromModel(itemModel);
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = convertStockFromModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);
        return itemModel;
    }

    private ItemDO convertFromModel(ItemModel itemModel) {
        if (itemModel == null) return null;
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        return itemDO;
    }
    
    private ItemStockDO convertStockFromModel(ItemModel itemModel) {
        if (itemModel == null) return null;
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    @Override
    public ItemModel getItemById(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
        ItemModel itemModel = converFromDO(itemDO, itemStockDO);
        PromoModel promoModel = promoService.getPromoByItemId(id);
        if (promoModel != null && promoModel.getStatus().intValue() != PromoModel.STATUS_END) {
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }
    
    private ItemModel converFromDO(ItemDO itemDO, ItemStockDO itemStockDO) {
        if (itemDO == null || itemStockDO == null) return null;
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }

    @Override
    public List<ItemModel> listItems() {
        List<ItemDO> itemDOs = itemDOMapper.listItems();
        List<ItemModel> itemModels =  itemDOs.stream().map((itemDO)->{
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = converFromDO(itemDO, itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModels;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        if (itemId == null || amount == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        int affectedRow = itemStockDOMapper.updateStock(itemId, amount);
        if (affectedRow == 0) throw new BusinessException(EmBusinessError.ITEM_STOCK_LIMIT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        if (itemId == null || amount == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        int affectedRow = itemDOMapper.updateSales(itemId, amount);
        if (affectedRow == 0) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }
}
