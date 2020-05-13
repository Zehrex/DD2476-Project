1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dao/ItemDOMapper.java
package com.loststars.quickbuy.dao;

import com.loststars.quickbuy.dataobject.ItemDO;
import com.loststars.quickbuy.dataobject.ItemDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    List<ItemDO> selectByExample(ItemDOExample example);

    ItemDO selectByPrimaryKey(Integer id);
    
    List<ItemDO> listItems();

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
    
    int updateSales(@Param("id") Integer id, @Param("amount") Integer amount);
}