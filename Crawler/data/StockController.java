11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/controller/StockController.java
package com.hyf.mtcc.demo.stock.controller;

import com.hyf.mtcc.demo.stock.request.DecreaseStockReq;
import com.hyf.mtcc.demo.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/decrease")
    public String decreaseStock(@RequestBody @Validated DecreaseStockReq req) {
        log.info("decreaseStock,req:{}", req);
        Integer decreaseResult = stockService.decrease(req);
        if (decreaseResult == null || decreaseResult == 0) {
            return "failure";
        }
        return "success";
    }

    @GetMapping("/findStockByName")
    public Map<String, Object> findStockByName(@RequestParam("productName") String productName) {
        return stockService.findStockByName(productName);
    }

}
