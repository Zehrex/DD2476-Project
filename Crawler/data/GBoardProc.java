2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/controller/gBoard/GBoardProc.java
package com.increpas.www.controller.gBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.www.controller.*;
import com.increpas.www.dao.*;

public class GBoardProc implements ClsController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/clsProj/gBoard/gBoardList.cls";
		req.setAttribute("isRedirect", true);
		String body = req.getParameter("body");
		String sid = (String) req.getSession().getAttribute("SID");
		GBoardDAO gDAO = new GBoardDAO();
		int cnt = gDAO.addData(sid, body);
		return view;
	}

}
