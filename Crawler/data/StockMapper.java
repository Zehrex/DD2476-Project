11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/dao/StockMapper.java
package com.hyf.mtcc.demo.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyf.mtcc.demo.stock.bean.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface StockMapper extends BaseMapper<Stock> {

    int lockStock(@Param("id") String id, @Param("lockStock") Integer lockStock, @Param("time") Date time);

    int lockStockConfirm(@Param("productName") String productName, @Param("lockStock") Integer lockStock, @Param("time") Date time);

    int lockStockCancel(@Param("productName") String productName, @Param("lockStock") Integer lockStock, @Param("time") Date time);

}
