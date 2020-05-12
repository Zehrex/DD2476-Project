1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dao/UserPasswordDOMapper.java
package com.loststars.quickbuy.dao;

import com.loststars.quickbuy.dataobject.UserPasswordDO;
import com.loststars.quickbuy.dataobject.UserPasswordDOExample;
import java.util.List;

public interface UserPasswordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPasswordDO record);

    int insertSelective(UserPasswordDO record);

    List<UserPasswordDO> selectByExample(UserPasswordDOExample example);

    UserPasswordDO selectByPrimaryKey(Integer id);
    
    UserPasswordDO selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserPasswordDO record);

    int updateByPrimaryKey(UserPasswordDO record);
}