2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/controller/member/LoginProc.java
package com.increpas.www.controller.member;

import javax.servlet.http.*;

import com.increpas.www.controller.*;
import com.increpas.www.dao.*;

public class LoginProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/main.cls";
		req.setAttribute("isRedirect", true);
		
		// 파라미터 가져오고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getLoginCnt(sid, spw);
		
		if(cnt == 1) {
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
		} else {
			view = "/clsProj/member/login.cls";
		}
		
		return view;
	}

}
