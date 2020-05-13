2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/controller/gBoard/GBoardWrite.java
package com.increpas.www.controller.gBoard;

import javax.servlet.http.*;

import com.increpas.www.controller.*;
import com.increpas.www.dao.*;

public class GBoardWrite implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/gBoard/gBoardWrite.jsp";
		// 만약 로그인 하진 않았거나 이미 방명록을 작성한 사람은 로그인페이지나 방명록 리스트페이지로 이동해야 한다.
		// 로그인 하지 않은 사람이 요청하는 경우 처리
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		if(sid == null || sid.length() == 0) {
			view = "/clsProj/member/login.cls";
			req.setAttribute("isRedirect", true);
		}
		// 이미 방명록을 작성한 사람이 요청하는 경우
		GBoardDAO gDAO = new GBoardDAO();
		int cnt = gDAO.getCnt(sid);
		if(cnt == 1) {
			view = "/clsProj/gBoard/gBoardList.cls";
			req.setAttribute("isRedirect", true);
		}
		return view;
	}

}
