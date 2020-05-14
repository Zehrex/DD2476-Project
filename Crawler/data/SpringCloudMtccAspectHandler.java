11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-spring-cloud/src/main/java/com/yf/mtcc/springcloud/aspectj/SpringCloudMtccAspectHandler.java
package com.yf.mtcc.springcloud.aspectj;

import com.yf.mtcc.common.context.MtccTransactionContext;
import com.yf.mtcc.common.enums.MtccRoleEnum;
import com.yf.mtcc.core.aspect.MtccAspectHandler;
import com.yf.mtcc.core.rpc.RpcMediator;
import com.yf.mtcc.core.service.MtccAspectHandlerService;
import com.yf.mtcc.core.threadlocal.MtccThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Component
@Slf4j
public class SpringCloudMtccAspectHandler implements MtccAspectHandler {

    private MtccAspectHandlerService aspectHandlerService;

    public SpringCloudMtccAspectHandler(MtccAspectHandlerService handlerService) {
        this.aspectHandlerService = handlerService;
    }

    @Override
    public Object handleAspectPointcutMethod(ProceedingJoinPoint pjp) throws Throwable {
        MtccTransactionContext context = MtccThreadLocalContext.INSTANCE.getContext();
        if (null == context) {
//            首先从请求头中获取上下文信息
            try {
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
                assert servletRequestAttributes != null;
                HttpServletRequest request = servletRequestAttributes.getRequest();
                context = RpcMediator.INSTANCE.acquire((request::getHeader));
                if (null != context) {
                    //此时角色是provider;
                    context.setRole(MtccRoleEnum.PROVIDER.getCode());
                }
                //如果还是为空，来到了发起者的事务处理，在那里面初始化上下文
            } catch (Exception e) {
                log.error("can not acquire context info", e);
            }
        } else {
            //context不为空说明拦截了消费者的feign接口，此时修改角色为consumer
            //在此之前，当前角色是发起者
            if (context.getRole() == MtccRoleEnum.INITIATOR.getCode()) {
                context.setRole(MtccRoleEnum.CONSUMER.getCode());
            }

        }
        return aspectHandlerService.invoke(context, pjp);
    }
}
