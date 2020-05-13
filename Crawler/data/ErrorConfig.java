2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/error/ErrorConfig.java
package com.example.lx.error;
/**
 * @Author lixiang
 * @Date 2020/5/10 9:09
 * @Version 1.0
 */
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/index.html");
        registry.addErrorPages(error404Page);
    }

}
