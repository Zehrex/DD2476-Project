2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/interceptor/LoginInterceptor.java
package com.example.lx.interceptor;
/**
 * @Author lixiang
 * @Date 2020/5/10 9:29
 * @Version 1.0
 */

import com.example.lx.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class LoginInterceptor  implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println(HttpStatus.NO_CONTENT.value());
        System.out.println(HttpMethod.OPTIONS.toString());
        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
//        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
//            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
//            return true;
//        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        System.out.println(subject.isAuthenticated());
        System.out.println(subject.isRemembered());
        if (!subject.isAuthenticated() && !subject.isRemembered() ) {
            System.out.println("请求被拦截");
            return false;
        }
        return true;
    }


}

