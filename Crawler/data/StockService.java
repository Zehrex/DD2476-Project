11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/service/StockService.java
package com.hyf.mtcc.demo.stock.service;

import com.hyf.mtcc.demo.stock.request.DecreaseStockReq;

import java.util.Map;

public interface StockService {

    Integer decrease(DecreaseStockReq req);

    Map<String, Object> findStockByName(String productName);

}
