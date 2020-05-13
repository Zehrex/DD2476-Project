1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/service/RolesService.java
package com.mybatis.service;

import com.mybatis.mapper.RolesMapper;
import com.mybatis.model.lotlot.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {
    @Autowired
    RolesMapper rolesMapper;

    /*查询全部*/
    public List<Role> findAll() {
        return rolesMapper.findAll();
    }

    /*角色用户联合查询*/
    public List<Role> findAllRoleAndUser() {
        return rolesMapper.findAllRoleAndUser();
    }
}
