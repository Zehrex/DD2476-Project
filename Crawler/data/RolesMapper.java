1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/mapper/RolesMapper.java
package com.mybatis.mapper;

import com.mybatis.model.lotlot.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RolesMapper {
    /*查询全部*/
    public List<Role> findAll();

    /*多表联合查询*/
    List<Role> findAllRoleAndUser();

}
