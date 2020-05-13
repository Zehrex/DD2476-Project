1
https://raw.githubusercontent.com/MichaelDYZ/springboot-thymeleaf/master/springboot-thymeleaf/src/main/java/com/thymeleaf/controller/StudentController.java
package com.thymeleaf.controller;

import com.thymeleaf.data.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dyz
 * @version 1.0
 * @date 2020/5/9 14:32
 */
@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {


    /**
     * 登录
     * @param student
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(Student student, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        mv.addObject(student);
        mv.setViewName("redirect:/");

        request.getSession().setAttribute("student", student);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }
}
