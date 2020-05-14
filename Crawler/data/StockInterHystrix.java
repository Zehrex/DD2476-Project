11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/inter/StockInterHystrix.java
package com.hyf.mtcc.demo.order.inter;

import com.hyf.mtcc.demo.order.request.DecreaseStockReq;
import com.yf.mtcc.common.exception.MtccHystrixException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@Component
public class StockInterHystrix implements StockInter {

    @Override
    public String decreaseStock(DecreaseStockReq req) {
        log.error("StockInterHystrix decreaseStock,req:{}", req);
        //熔断需要抛出mtccHystrixException异常
        throw new MtccHystrixException("远程调用StockInter超时");
    }

    @Override
    public Map<String, Object> findStockByName(String productName) {
        log.error("StockInterHystrix findStockByName,productName:{}", productName);
        throw new MtccHystrixException("远程调用StockInter超时");
    }

}
