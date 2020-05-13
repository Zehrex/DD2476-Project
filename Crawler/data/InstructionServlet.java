2
https://raw.githubusercontent.com/SShivani/Online-Quiz-Project/master/WEB-INF/classes/InstructionServlet.java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class InstructionServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("NAME");
		String pwd=request.getParameter("password");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from JavaQuiz");
			int c=0;
			while(rs.next())
			{
				if(rs.getString(1).equals(name) && rs.getString(4).equals(pwd))
				{c=1;
				break;
				}
			}
			st.close();
			con.close();

			out.print("<head><style>");
			out.print("body{padding:0%;margin:0%;}");
			out.print(".box form h1{padding:0%;margin:0%;}");
			out.print(".next{background-color:green;color:white;width:10%;font-size:15px;border-radius:35%;padding:10px;margin:2% 19%;}");
			out.print("form{padding:0%;margin:0%;}");
			out.print(".image{padding:0%;border-radius:15%;}");
			out.print(".box{background-color:pink;color:white;margin:5%;padding:5%;border-radius:15%;}");
			out.print(".inst h2{color:green;}");
			out.print(".inst h3{padding:0%;margin:0%;}");
			out.print(".inst{width:70%;padding-left:30%;}");
			out.print("</style></head>");
			if(c==1)
			{
				out.print("<body>");
				out.print("<div class=\"box\">");
				out.print("<form action=quiz.com method=post>");
				out.print("<div class=\"image\"><img src=\"img.jfif\" width=\"100%\" height=\"30%\"></div>");
				out.print("<h1 align=center>ExQuizMe</h1><br>");
				out.print("<h1 align=center>Quiz On Java</h1><br>");
				out.print("<h1 align=center>=====================</h1><br><br>");
				out.print("<div class=\"inst\">");
				out.print("<h2>Read the following instructions before proceeding</h2><br>");
				out.print("<h3>1. This test consists of 10 questions on java.</h3><br>");
				out.print("<h3>2. Each question carries 1 mark.</h3><br>");
				out.print("<h3>3. Questions are of multiple choice type</h3><br>");
				out.print("<input type=submit value=\"Start Quiz\" class=next>");
				out.print("</div>");
				out.print("</form>");
				out.print("</div>");
				out.print("</body>");  
			}
			else
			{
				out.print("<body bgcolor=pink text=white>");
				out.print("<form action=welcome.html method=post>");
				out.print("<h2 align=center>LOGIN..!!</h2>");
				out.print("<hr><br></br>");
				out.print("<h2 align=center>Authentication Failed...Please re-enter your details!!....</h2>");
				out.print("<table align=center>");
				out.print("<tr><td><input type=submit value=Back class=next></td></tr>");
				out.print("</table></form></body>");
			}
		
		}
		catch(SQLException | ClassNotFoundException e)
		{
			out.print("<body bgcolor=pink text=white>");
			out.print("<form action=welcome.html method=post>");
			out.print("<h2 align=center>LOGIN..!!</h2>");
			out.print("<hr><br></br>");
			out.print("<h2 align=center>Authentication Failed...Please re-enter your details!!"+e+"....</h2>");
			out.print("<table align=center>");
			out.print("<tr><td><input type=submit value=Back class=next></td></tr>");
			out.print("</table></form></body>");
		}
		out.close();
	}
}