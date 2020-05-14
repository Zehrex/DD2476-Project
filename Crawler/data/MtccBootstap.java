11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/bootstrap/MtccBootstap.java
package com.yf.mtcc.core.bootstrap;

import com.yf.mtcc.common.config.MtccConfig;
import com.yf.mtcc.core.service.MtccBootstrapService;
import com.yf.mtcc.core.utils.SpringBeanACU;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
public class MtccBootstap implements ApplicationContextAware {

    private MtccConfig mtccConfig;

    private MtccBootstrapService bootstrapService;

    @Autowired
    public MtccBootstap(MtccConfig mtccConfig, MtccBootstrapService mtccBootstrapService) {
        this.mtccConfig = mtccConfig;
        this.bootstrapService = mtccBootstrapService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanACU.getInstance().setCfgContext((ConfigurableApplicationContext) applicationContext);
        bootstrapService.initMtccTransaction(mtccConfig);
    }
}
