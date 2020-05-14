11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/inter/StockInter.java
package com.hyf.mtcc.demo.order.inter;

import com.hyf.mtcc.demo.order.request.DecreaseStockReq;
import com.yf.mtcc.core.annotation.Mtcc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "cloud-stock", fallback = StockInterHystrix.class)
public interface StockInter {

    @Mtcc
    @PostMapping("/stock/decrease")
    String decreaseStock(@RequestBody @Validated DecreaseStockReq req);

    @GetMapping("/stock/findStockByName")
    Map<String, Object> findStockByName(@RequestParam("productName") String productName);

}
