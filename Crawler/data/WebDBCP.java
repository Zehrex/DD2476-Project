2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/DB/WebDBCP.java
package com.increpas.www.DB;

/**
 * 이 클래스는 커넥션 풀에 있는 커넥션을 이용해서
 * 데이터베이스 작업을 할 유틸리티 성격을 가진 클래스이다.
 * 
 * @author	전은석
 * @since	2020/05/08
 * 
 */
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class WebDBCP {
	// 커넥션 풀을 관리할 클래스 변수 준비
	public DataSource ds;
	
	public WebDBCP() {
		/*
			 지금 이클래스를 누군가 new 시키면
			 context.xml 파일에 등록된 자원을 가지고 오도록 한다.
			 이처럼 context.xml 파일에 등록된 자원을 가지고 오는 기법을
			 JNDI(Java Naming and Directory Inteface) 기법이라고 한다.
		 */
		
		try {
			// 1. context.xml 파일에 등록된 자원을 알아낸다.
			InitialContext context = new InitialContext();
			// 2. 그 중에서 필요한 자원을 얻어낸다.
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
			/*
				찾을 이름을 정하는 규칙
				
					java:/comp/env/찾을이름
					
				위 작업이 성공하면 커넥션 풀을 찾았고 사용할 수 있게 된다.
			 */
		} catch(Exception e) {
			System.out.println("######### 커넥션 풀 가져오기 실패 #########");
		}
	}
	
	/*
	 * 필요한 순간에 접속을 해야겠다.
	 * 물론 접속은 직접 하는 것이 아니고
	 * 커넥션풀이 가지고 있는 커넥션을 빌려오는 것이다.
	 */
	public Connection getCon() {
		Connection con = null;
		try {
			// 커넥션 풀을 관리하는 DataSource에서 꺼내온다.
			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// Statement 반환해주는 함수
	public Statement getSTMT(Connection con) {
		Statement stmt = null;
		try {
//			stmt = con.createStatement();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	// PreparedStatement 반환해주는 함수
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
//			pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	// 사용하지 않는 자원 반환해주는 함수
	public void close(Object o) {
		try {
			if(o instanceof Connection) {
				((Connection) o).close();
			} else if(o instanceof Statement) {
				((Statement) o).close();
			} else if(o instanceof PreparedStatement) {
				((PreparedStatement) o).close();
			} else if(o instanceof ResultSet) {
				((ResultSet) o).close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
