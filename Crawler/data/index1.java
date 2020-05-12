2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/controller/index1.java
package cn.blog.controller;

/**
 * @author chenval
 * @date 2020/3/16 23:04
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class index1 {
    @GetMapping(value = "site/hpp/index1")
    public String blogIndex(){
        return "site/hpp/index1";
    }
}
