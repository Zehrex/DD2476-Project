1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/ItemService.java
package com.loststars.quickbuy.service;

import java.util.List;

import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.service.model.ItemModel;

public interface ItemService {

    public ItemModel createItem(ItemModel itemModel) throws BusinessException;
    
    public ItemModel getItemById(Integer id) throws BusinessException;
    
    public List<ItemModel> listItems();
    
    public void decreaseStock(Integer itemId, Integer amount) throws BusinessException;
    
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException;
}
