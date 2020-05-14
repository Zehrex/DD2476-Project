11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/utils/SpringBeanACU.java
package com.yf.mtcc.core.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */

public class SpringBeanACU {

    private static final SpringBeanACU INSTANCE = new SpringBeanACU();

    private ConfigurableApplicationContext cfgContext;

    private SpringBeanACU() {
        if (INSTANCE != null) {
            throw new Error("error");
        }
    }


    public static SpringBeanACU getInstance() {
        return INSTANCE;
    }


    public <T> T getBean(final Class<T> type) {
        T bean;
        try {
            bean = cfgContext.getBean(type);
        } catch (BeansException e) {
            bean = getByName(type);
        }
        return bean;
    }

    private <T> T getByName(Class<T> type) {
        T bean;
        String className = type.getSimpleName();
        bean = cfgContext.getBean(StringUtils.uncapitalize(className), type);
        return bean;
    }



    public void registerBean(final String beanName, final Object obj) {
        cfgContext.getBeanFactory().registerSingleton(beanName, obj);
    }

    public void setCfgContext(final ConfigurableApplicationContext cfgContext) {
        this.cfgContext = cfgContext;
    }

}
