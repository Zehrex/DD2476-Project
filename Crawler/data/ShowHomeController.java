8
https://raw.githubusercontent.com/nataraz123/Spring/master/MVCAnnno12-ShomeHomePage/src/main/java/com/nt/controller/ShowHomeController.java
package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowHomeController {

	 @RequestMapping("/home.htm")
	public String  home() {
		return "welcome";
	}
}
