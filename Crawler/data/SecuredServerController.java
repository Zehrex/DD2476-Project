3
https://raw.githubusercontent.com/mqxu/spring-boot-review/master/spring-boot-config/src/main/java/com/soft1851/springboot/config/controller/SecuredServerController.java
package com.soft1851.springboot.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author: mq_xu
 * @date: 2020/5/12
 * @description:HTTPS测试
 */
@RestController
@Slf4j
class SecuredServerController {

    @RequestMapping("/secured")
    public String secured() {
        log.info("Inside secured()");
        return "Hello user !!! : " + LocalDateTime.now();
    }
}