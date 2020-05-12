1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/service/UserService.java
package com.mybatis.service;

import com.mybatis.mapper.UserMapper;
import com.mybatis.model.lotlot.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /*查询全部*/
    public List<User> findAll() {
        return userMapper.findAll();
    }
    /*多表查询*/
    public List<User> findAllUserAndRole(){
        return userMapper.findAllUserAndRole();
    }
}
