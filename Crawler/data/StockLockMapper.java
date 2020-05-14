11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/dao/StockLockMapper.java
package com.hyf.mtcc.demo.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyf.mtcc.demo.stock.bean.StockLock;
import org.apache.ibatis.annotations.Param;

public interface StockLockMapper extends BaseMapper<StockLock> {

    int deleteLockRecord(@Param("orderId") String orderId);

}
