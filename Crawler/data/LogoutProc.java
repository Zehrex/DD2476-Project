2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/controller/member/LogoutProc.java
package com.increpas.www.controller.member;

import javax.servlet.http.*;

import com.increpas.www.controller.ClsController;

public class LogoutProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/main.cls";
		req.setAttribute("isRedirect", true);
		HttpSession session = req.getSession();
		session.removeAttribute("SID");
		return view;
	}

}
