14
https://raw.githubusercontent.com/fawad1997/SpringWebAPI/master/src/main/java/com/restfulspring/apiexample/controller/HelloController.java
package com.restfulspring.apiexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/")
    public String sayHello(){
        return "Hello World!";
    }
}
