11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-account/src/main/java/com/hyf/mtcc/demo/account/dao/AccountFreezeMapper.java
package com.hyf.mtcc.demo.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyf.mtcc.demo.account.bean.AccountFreeze;
import org.apache.ibatis.annotations.Param;

public interface AccountFreezeMapper extends BaseMapper<AccountFreeze> {

    int deleteFreezeRecord(@Param("orderId") String orderId);

}
