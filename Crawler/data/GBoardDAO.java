2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/dao/GBoardDAO.java
package com.increpas.www.dao;

/**
 * 이 클래스는 방명록게시판에 관련된 데이터베이스 작업을 처리할 클래스
 * @author	전은석
 * @since	2020.05.12
 * @version	v.1.0
 */
import com.increpas.www.DB.*;
import com.increpas.www.sql.*;
import com.increpas.www.vo.*;

import java.sql.*;
import java.util.*;

public class GBoardDAO {
	WebDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	GBoardSQL gSQL;
	GBoardVO gVO;
	
	public GBoardDAO() {
		db = new WebDBCP();
		gSQL = new GBoardSQL();
	}
	
	// 방명록 리스트 조회 전담 처리 함수
	public ArrayList<GBoardVO> getGList(){
		ArrayList<GBoardVO> list = new ArrayList<GBoardVO>();
		// 할일
		// 1. 커넥션 가져오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = gSQL.getSQL(gSQL.SEL_LIST);
		// 3. stmt 가져오고
		stmt = db.getSTMT(con);
		try {
			// 4. 질의명령 stmt에 실어서 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			// 5. 하나씩 꺼내서 VO에 담고
			while(rs.next()) {
				// vo 만들고
				GBoardVO vo = new GBoardVO();
				vo.setGno(rs.getInt("gno"));
				vo.setGmno(rs.getInt("gmno"));
				vo.setId(rs.getString("id"));
				vo.setBody(rs.getString("body"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setgDate(rs.getDate("gdate"));
				vo.setgTime(rs.getTime("gdate"));
				vo.setsDate();
				// 6. list에 VO담고
				list.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		// 7. list 내보내고
		return list;
	}
	
	// 작성글 카운트조회 디비작업 전담 처리 함수
	public int getCnt(String id) {
		int cnt = 0 ;
		// 할일
		// 1. 커넥션 가져오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = gSQL.getSQL(gSQL.SEL_ID_CNT);
		// 3. pstmt 가져오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성해봅시다.
			pstmt.setString(1, id);
			// 5. 보낼까요???
			rs = pstmt.executeQuery();
			// 6. 꺼낼까요???
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 7. 내보낼까요???
		return cnt;
	}
	
	// 아바타 파일이름 조회 전담 처리함수
	public String getAvt(String id) {
		String avt = "";
		// 할일
		// 1. 커넥션 가져오고
		con = db.getCon();
		// 2. 질의명령가져오고
		String sql = gSQL.getSQL(gSQL.SEL_AVT);
		// 3. pstmt 가져오고
		pstmt = db.getPSTMT(con, sql);
		try{
			// 4. 질의명령 완성하고
			pstmt.setString(1, id);
			// 5. 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 데이터꺼내고
			rs.next();
			avt = rs.getString("avatar");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 7. 내보내고
		return avt;
	}
	
	// 방명록 데이터베이스 입력 전담 처리 함수
	public int addData(String id, String body) {
		int cnt = 0 ;
		// 1.
		con = db.getCon();
		// 2.
		String sql = gSQL.getSQL(gSQL.ADD_DATA);
		// 3. 
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4.
			pstmt.setString(1, id);
			pstmt.setString(2, body);
			// 5. 
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 6.
		return cnt;
	}
}
