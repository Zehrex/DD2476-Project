2
https://raw.githubusercontent.com/increpas-cls2/clsProj/branch/clsProj/src/com/increpas/www/sql/GBoardSQL.java
package com.increpas.www.sql;

public class GBoardSQL {
	public final int SEL_LIST = 1001;
	public final int SEL_ID_CNT = 1002;
	public final int SEL_AVT = 1003;
	
	public final int ADD_DATA = 2001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_LIST:
			buff.append("SELECT "); 
			buff.append("	gno, gmno, id, gdate, gbody body, savename avatar "); 
			buff.append("FROM ");
			buff.append("	gboard, member m, avatar a ");
			buff.append("WHERE ");
			buff.append("	gmno = mno "); 
			buff.append("	AND m.ano = a.ano ");
			break;
		case SEL_ID_CNT:
			buff.append("SELECT "); 
			buff.append("	count(*) cnt "); 
			buff.append("FROM ");
			buff.append("	gboard ");
			buff.append("WHERE ");
			buff.append("	gmno = "); 
			buff.append("	(SELECT mno FROM member WHERE id = ? ) ");
			break;
		case SEL_AVT:
			buff.append("SELECT ");
			buff.append("    savename avatar ");
			buff.append("FROM ");
			buff.append("    member m, avatar a ");
			buff.append("WHERE ");
			buff.append("    m.ano = a.ano "); 
			buff.append("    AND id = ? ");
			break;
		case ADD_DATA:
			buff.append("INSERT INTO ");
			buff.append("    gboard(gno, gmno, gbody) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(GNO)+1, 1000) FROM gboard), ");
			buff.append("    (SELECT mno FROM MEMBER WHERE id = ? ), ? ");
			buff.append(")");
			break;
		}
		
		return buff.toString();
	}
}
