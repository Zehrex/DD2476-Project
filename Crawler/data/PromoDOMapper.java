1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dao/PromoDOMapper.java
package com.loststars.quickbuy.dao;

import com.loststars.quickbuy.dataobject.PromoDO;
import com.loststars.quickbuy.dataobject.PromoDOExample;
import java.util.List;

public interface PromoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromoDO record);

    int insertSelective(PromoDO record);

    List<PromoDO> selectByExample(PromoDOExample example);

    PromoDO selectByPrimaryKey(Integer id);
    
    PromoDO selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(PromoDO record);

    int updateByPrimaryKey(PromoDO record);
}