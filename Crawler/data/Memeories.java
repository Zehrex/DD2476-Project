2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/controller/Memeories.java
package cn.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenval
 * @date 2020/3/16 23:08
 */
@Controller
public class Memeories {
    @GetMapping(value = "/BirthdayCake")
    public String blogIndex(){
        return "site/hpp/Memories";
    }
}
