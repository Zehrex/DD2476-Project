11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/impl/MtccBootstrapServiceImpl.java
package com.yf.mtcc.core.service.impl;

import com.yf.mtcc.common.config.MtccConfig;
import com.yf.mtcc.core.service.MtccBootstrapService;
import com.yf.mtcc.core.service.MtccTransactionLogDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class MtccBootstrapServiceImpl implements MtccBootstrapService {
    @Autowired
    private MtccTransactionLogDaoService logService;

    @Override
    public void initMtccTransaction(MtccConfig mtccConfig) {
        log.info("初始化事务日志表...");
        try{
            logService.init(mtccConfig);
        }catch (Exception e) {
            log.error("初始化事务日志失败..");
        }
    }
}
