1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/mapper/AccountMapper.java
package com.mybatis.mapper;

import com.mybatis.model.single.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper {
    /*查询全部*/
    public List<Account> findAll();

    /*根据大于money条件查询*/
    List<Account> findSomeBigThanMoney(@Param("small_money") Float small_money,
                                       @Param("big_money") Float big_money);

    /*批量的添加数据*/
    void insertSome(List<Account> list);
}
