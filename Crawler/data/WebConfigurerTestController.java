2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/test/java/com/gardle/config/WebConfigurerTestController.java
package com.gardle.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebConfigurerTestController {

    @GetMapping("/api/v1/test-cors")
    public void testCorsOnApiPath() {
    }

    @GetMapping("/test/test-cors")
    public void testCorsOnOtherPath() {
    }
}
