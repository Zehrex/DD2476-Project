2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/controller/DefineDataController.java
package com.chad.wechatdev.controller;

import com.chad.wechatdev.commons.utils.ResponseCodes;
import com.chad.wechatdev.commons.utils.ResponseResult;
import com.chad.wechatdev.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/*
* 开头使用 @RestController 注解，接口不需要特别加入 @ResponseBody 注解
* */
@RestController
public class DefineDataController {
    Logger logger = LoggerFactory.getLogger(DefineDataController.class);


    @GetMapping(value = "/testget")
    public Map<String,String> wechatUserCallback(@RequestParam(value = "code", required = false) String code, @SessionAttribute String user) {
        logger.info("从session 中取值："+user);
        Map<String, String> data = new HashMap<>();
        data.put("name", "chad");

        return data;
    }

    /**
     * 传参一个 user 对象
     * @param user
     * @return
     */
    @PostMapping("/testpost")
    public  ResponseResult addCaioneData(@RequestBody User user, HttpServletRequest request){
        ResponseResult responseResult = new ResponseResult();
        try{
            responseResult.setVo(null);
            responseResult.setResult(ResponseCodes.Success.getCode(),ResponseCodes.Success.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            responseResult.setResult(ResponseCodes.UnKnownError.getCode(),ResponseCodes.UnKnownError.getMessage());
        }
        return responseResult;
    }

}
