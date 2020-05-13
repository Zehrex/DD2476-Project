2
https://raw.githubusercontent.com/WhiteFerrari666/Medication/develop/src/main/java/controller/TestController.java
package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public String home() {
        return "Spring boot is working!";
    }
}
