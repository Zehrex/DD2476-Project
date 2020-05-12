1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dao/ItemStockDOMapper.java
package com.loststars.quickbuy.dao;

import com.loststars.quickbuy.dataobject.ItemStockDO;
import com.loststars.quickbuy.dataobject.ItemStockDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemStockDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDO record);

    int insertSelective(ItemStockDO record);

    List<ItemStockDO> selectByExample(ItemStockDOExample example);

    ItemStockDO selectByPrimaryKey(Integer id);
    
    ItemStockDO selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(ItemStockDO record);

    int updateByPrimaryKey(ItemStockDO record);
    
    int updateStock(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}