3
https://raw.githubusercontent.com/mqxu/spring-boot-review/master/spring-boot-start/src/main/java/com/soft1851/springboot/start/controller/SpringWebController.java
package com.soft1851.springboot.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mq_xu
 * @description : 标准的Spring MVC控制器
 */
@Controller
@RequestMapping("/springWeb")
@ResponseBody
public class SpringWebController {
    @RequestMapping("/hello")
    public String hello() {
        return "<h2>Hello, Spring Boot!<h2>";
    }
}
