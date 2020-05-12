1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/service/AccountService.java
package com.mybatis.service;

import com.mybatis.mapper.AccountMapper;
import com.mybatis.model.single.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;

    /*查询全部*/
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    /*根据大于条件查询*/
    public List<Account> findSomeBigThanMoney(Float small, Float big) {
        return accountMapper.findSomeBigThanMoney(small, big);
    }

    /*批量的添加*/
    public void insertSome(List<Account> list) {
        accountMapper.insertSome(list);
    }
}
