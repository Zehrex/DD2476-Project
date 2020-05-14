11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-spring-cloud/src/main/java/com/yf/mtcc/springcloud/aspectj/SpringCloudMtccAspect.java
package com.yf.mtcc.springcloud.aspectj;

import com.yf.mtcc.core.aspect.AbstractMtccAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringCloudMtccAspect extends AbstractMtccAspect {

    @Autowired
    public SpringCloudMtccAspect(SpringCloudMtccAspectHandler handler) {
        super.setAspectHandler(handler);
    }


}
