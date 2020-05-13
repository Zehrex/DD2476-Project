2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/controller/HtmlPassValueController.java
package com.chad.wechatdev.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller  //注意这里必须为Controller
public class HtmlPassValueController {
    Logger logger = LoggerFactory.getLogger(HtmlPassValueController.class);

    /**
     * 本地访问内容地址 ：http://localhost:8080/oauth
     * @param map
     * @return
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String helloHtml(@RequestParam(value = "value",required = false) String value, HashMap<String, Object> map, Model model, HttpServletRequest request) {

//        System.out.println(nickname+"======AAAA======="+image);

        logger.info("拿到index页面传来的值:"+value);
        map.put("test", request.getSession().getAttribute("user"));
        map.put("value", value);

        return "test";
    }

}
