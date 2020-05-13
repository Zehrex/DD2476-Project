6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/swagger/SwaggerProperties.java
package com.github.taoroot.taoiot.swagger;

import cn.hutool.core.util.ReUtil;
import com.github.taoroot.taoiot.common.Const;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : zhiyi
 * Date: 2020/2/17
 */
@Log4j2
@Data
@ConfigurationProperties(prefix = Const.PREFIX + ".swagger")
public class SwaggerProperties implements InitializingBean {

    /**
     * 名称
     */
    private String name = "swagger";

    /**
     * 版本号
     */
    private String version = "0.1";

    /**
     * 前缀
     */
    private String prefix = "/";

    /**
     * 验证码接口
     */
    private List<String> excludePath = new ArrayList<>();


    @Resource
    private WebApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        // 一些默认开放的接口
        excludePath.addAll(Arrays.asList("/error", "/actuator/**"));

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        // 收集 NotAuth 注解的接口
        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NotSwagger.class))
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> excludePath.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*"))));

            Optional.ofNullable(AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), NotSwagger.class))
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> excludePath.add(ReUtil.replaceAll(url, "\\{(.*?)\\}", "*"))));
        });

        log.info("swagger exclude urls: {}", excludePath);
    }
}
