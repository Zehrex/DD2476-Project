2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/dao/MemberDAO.java
package com.increpas.www.dao;

import java.sql.*;
import com.increpas.www.DB.*;
import com.increpas.www.sql.*;

public class MemberDAO {
	WebDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	MemberSQL mSQL;
	public MemberDAO() {
		db = new WebDBCP();
		mSQL = new MemberSQL();
	}
	
	// 로그인 데이터베이스 처리 전담 함수
	public int getLoginCnt(String id, String pw) {
		int cnt = 0 ;
		con = db.getCon();
		// sql
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN_CNT);
		
		// pstmt 가져오고
		pstmt = db.getPSTMT(con, sql);
		
		try{
			// 질의 명령 완성
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			// 질의명령보내고 결과 받고
			rs = pstmt.executeQuery();
			// 데이터꺼내서 변수에 담고
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
}
