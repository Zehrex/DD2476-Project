2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/controller/AboutmeController.java
package cn.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutmeController {
    @GetMapping(value = "/aboutme")
    public String blogIndex(){
        return "site/aboutme";
    }
}
