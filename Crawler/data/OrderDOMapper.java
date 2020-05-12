1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dao/OrderDOMapper.java
package com.loststars.quickbuy.dao;

import com.loststars.quickbuy.dataobject.OrderDO;
import com.loststars.quickbuy.dataobject.OrderDOExample;
import java.util.List;

public interface OrderDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExample(OrderDOExample example);

    OrderDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}