11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-stock/src/main/java/com/hyf/mtcc/demo/stock/service/impl/StockServiceImpl.java
package com.hyf.mtcc.demo.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyf.mtcc.demo.stock.bean.Stock;
import com.hyf.mtcc.demo.stock.bean.StockLock;
import com.hyf.mtcc.demo.stock.dao.StockLockMapper;
import com.hyf.mtcc.demo.stock.dao.StockMapper;
import com.hyf.mtcc.demo.stock.request.DecreaseStockReq;
import com.hyf.mtcc.demo.stock.service.StockService;
import com.yf.mtcc.core.annotation.Mtcc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descrtption StockServiceImpl
 * @Author Elvis
 * @Date 2019/9/25
 **/
@Slf4j
@Service
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Resource
    private StockLockMapper stockLockMapper;

    @Transactional(rollbackFor = Exception.class)
    @Mtcc(confirmMethod = "decreaseStockConfirm", cancelMethod = "decreaseStockCancel")
    @Override
    public Integer decrease(DecreaseStockReq req) {
        log.info("==================decrease开始=====================req:{}" + req);
        String productName = req.getProductName();
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("product_name", productName);
        Stock dbStock = stockMapper.selectOne(wrapper);
        int result = 0;
        if (dbStock != null) {
//            锁定库存
            result = stockMapper.lockStock(dbStock.getId(), req.getStock(), new Date());
            if (result > 0) {
                StockLock stockLock = new StockLock();
                stockLock.setOrderId(req.getOrderId());
                stockLock.setLockStock(req.getStock());
                stockLock.setProductId(dbStock.getId());
                stockLock.setCreateTime(new Date());
                result = stockLockMapper.insert(stockLock);
            }
        }
        log.info("==================decrease结束=====================req:{}" + req);
        return result;
    }

    @Override
    public Map<String, Object> findStockByName(String productName) {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("product_name", productName);
        Stock dbStock = stockMapper.selectOne(wrapper);
        if (dbStock != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", dbStock.getId());
            map.put("price", dbStock.getPrice());
            return map;
        }
        return null;
    }

    //confirm
    @Transactional(rollbackFor = Exception.class)
    public Integer decreaseStockConfirm(DecreaseStockReq req) {
        log.info("==================decreaseStockConfirm开始=====================req:{}" + req);
        QueryWrapper<StockLock> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", req.getOrderId());
        StockLock stockLock = stockLockMapper.selectOne(wrapper);
        int result = 0;
        if (stockLock != null) {
            result = stockLockMapper.deleteById(stockLock.getId());
            if (result > 0) {
                result = stockMapper.lockStockConfirm(req.getProductName(), req.getStock(), new Date());
            }
        }
        log.info("==================decreaseStockConfirm结束=====================req:{}" + req);
        return result;
    }

    //cancel
    @Transactional(rollbackFor = Exception.class)
    public Integer decreaseStockCancel(DecreaseStockReq req) {
        log.info("==================decreaseStockCancel开始=====================req:{}" + req);
        QueryWrapper<StockLock> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", req.getOrderId());
        StockLock stockLock = stockLockMapper.selectOne(wrapper);
        int result = 0;
        if (stockLock != null) {
            result = stockLockMapper.deleteById(stockLock.getId());
            if (result > 0) {
                result = stockMapper.lockStockCancel(req.getProductName(), req.getStock(), new Date());
            }
        }
        log.info("==================decreaseStockCancel结束=====================req:{}" + req);
        return result;
    }

}
