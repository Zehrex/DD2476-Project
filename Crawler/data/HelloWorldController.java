2
https://raw.githubusercontent.com/joneconsulting/springbootproject/master/src/main/java/com/example/demo/controller/HelloWorldController.java
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

@RestController
public class HelloWorldController {
    // injection
    // 1. annotation -> @Autowired
    // 2. constructor
    // 3. setter method

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return "Good morning";
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(
            @RequestHeader(name="Accept-Language", required = false)
            Locale locale) {
        return messageSource.getMessage(
                "greeting.message", null, locale);
    }

}
