2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/controller/OauthController.java
package com.chad.wechatdev.controller;


import com.chad.wechatdev.commons.config.WechatMpProperties;
import com.chad.wechatdev.commons.service.UserService;
import com.chad.wechatdev.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class OauthController {
    Logger logger = LoggerFactory.getLogger(OauthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private WechatMpProperties weChatConfig;

    /**
     * 用户授权成功，获取微信回调的code
     */
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String wechatUserCallback(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "state") String state, HttpServletResponse response, HashMap<String, Object> map, Model model, HttpServletRequest request) {
        logger.info("=====拿到code=====");
        if (code == null) {
            //model.addAttribute("msg", "用户授权失败 未获取到code");
            return "error";
        }
        logger.info(code);

        logger.info("=====拿到授权带参 state=====");
        if (state == null) {
            //处理授权二维码带参  state
            return "error";
        }
        logger.info(state);

        User user = userService.saveWeChatUser(code, weChatConfig.getAppId(), weChatConfig.getSecret());
        //判断是否取到用户信息  
        if (user == null) {
            //获取失败 返回失败信息到 error.html
            logger.info("获取用户信息失败");
            model.addAttribute("msg", "获取用户信息失败");
            return "error";
        } else {
            //true 储存到session中，并传值到index.html
            request.getSession().setAttribute("user", user.getName());
            request.getSession().setAttribute("image", user.getHeadImg());

            map.put("user", user.getName());
            map.put("headimg", user.getHeadImg());
            return "index";
        }
    }


}
