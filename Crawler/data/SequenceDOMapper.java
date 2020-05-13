1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dao/SequenceDOMapper.java
package com.loststars.quickbuy.dao;

import com.loststars.quickbuy.dataobject.SequenceDO;
import com.loststars.quickbuy.dataobject.SequenceDOExample;
import java.util.List;

public interface SequenceDOMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequenceDO record);

    int insertSelective(SequenceDO record);

    List<SequenceDO> selectByExample(SequenceDOExample example);

    SequenceDO selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequenceDO record);

    int updateByPrimaryKey(SequenceDO record);
}