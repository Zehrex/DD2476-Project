1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/mapper/UserMapper.java
package com.mybatis.mapper;

import com.mybatis.model.lotlot.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    /*查询全部*/
    public List<User> findAll();

    /*多表联合查询*/
    List<User> findAllUserAndRole();

}
