2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/controller/member/LoginForm.java
package com.increpas.www.controller.member;

import javax.servlet.http.*;

import com.increpas.www.controller.*;

public class LoginForm implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/member/Login.jsp";
		return view;
	}

}
