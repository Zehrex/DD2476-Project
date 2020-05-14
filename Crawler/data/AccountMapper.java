11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-account/src/main/java/com/hyf/mtcc/demo/account/dao/AccountMapper.java
package com.hyf.mtcc.demo.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyf.mtcc.demo.account.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface AccountMapper extends BaseMapper<Account> {

    int freezeAmount(@Param("id") String id, @Param("freezeAmount") BigDecimal freezeAmount, @Param("time") Date time);

    int freezeAmountConfirm(@Param("username") String username, @Param("freezeAmount") BigDecimal amount, @Param("time") Date time);

    int freezeAmountCancel(@Param("username") String username, @Param("freezeAmount") BigDecimal amount, @Param("time") Date time);

}
