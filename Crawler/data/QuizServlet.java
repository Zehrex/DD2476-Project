2
https://raw.githubusercontent.com/SShivani/Online-Quiz-Project/master/WEB-INF/classes/QuizServlet.java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class QuizServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		out.print("<head><style>");
		out.print("body{padding:0%;margin:0%;}");
		out.print(".button1{text-align:center;}");
		out.print(".next{background-color:green;color:white;width:10%;font-size:15px;border-radius:35%;padding:10px;}");
		out.print(".box form h1{padding:0%;margin:0%;}");
		out.print("form{padding:0%;margin:0%;}");
		out.print("table{padding-top:0%;}");
		out.print(".image{padding:0%;border-radius:15%;}");
out.print(".box{background-color:pink;color:white;margin:5%;padding:5%;border-radius:15%;}");
		out.print("</style></head>");
		out.print("<body>");
		out.print("<div class=\"box\">");
		out.print("<form action=\"second.com\" method=post>");
		out.print("<div class=\"image\"><img src=\"img.jfif\" width=\"100%\" height=\"30%\"></div>");
		out.print("<h1 align=center>ExQuizMe</h1><br>");
		out.print("<h1 align=center>Quiz On Java</h1>");
		out.print("<table align=center><tr><td>");
		out.print("<h3 align=left>Q1. Who starts the default thread available in Java program?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q1 value=option1>System class</td></tr>");
		out.print("<tr><td><input type=radio name=Q1 value=option2>Main method</td></tr>");
		out.print("<tr><td><input type=radio name=Q1 value=option3>Static keyword</td></tr>");
		out.print("<tr><td><input type=radio name=Q1 value=option4>None of these<br></td></tr>");

		out.print("<tr><td><br><h3 align=left>Q2. What is meant by API?</h3>");
        out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q2 value=option1>Application Programming Interface</td></tr>");
		out.print("<tr><td><input type=radio name=Q2 value=option2>Application Programming Infrastructure</td></tr>");
		out.print("<tr><td><input type=radio name=Q2 value=option3>Advance Programming Interface</td></tr>");
		out.print("<tr><td><input type=radio name=Q2 value=option4>Advance Programming Infrastructure<br></td></tr>");

		out.print("<tr><td><br><h3 align=left>Q3. Which is the first method to be called by the browser in applet?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q3 value=option1>start method</td></tr>");
		out.print("<tr><td><input type=radio name=Q3 value=option2>init method</td></tr>");
		out.print("<tr><td><input type=radio name=Q3 value=option3>stop method</td></tr>");
		out.print("<tr><td><input type=radio name=Q3 value=option4>destroy method<br></td></tr>");

		out.print("<tr><td><br><h3 align=left>Q4. All collection classes are available in _____ package?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q4 value=option1>java.io</td></tr>");
		out.print("<tr><td><input type=radio name=Q4 value=option2>java.awt</td></tr>");
		out.print("<tr><td><input type=radio name=Q4 value=option3>java.lang</td></tr>");
		out.print("<tr><td><input type=radio name=Q4 value=option4>java.util<br></td></tr>");

		out.print("<tr><td><br><h3 align=left>Q5. Which of these keywords is not a part of exception handling?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q5 value=option1>try</td></tr>");
		out.print("<tr><td><input type=radio name=Q5 value=option2>catch</td></tr>");
		out.print("<tr><td><input type=radio name=Q5 value=option3>thrown</td></tr>");
		out.print("<tr><td><input type=radio name=Q5 value=option4>finally<br></td></tr>");     

		out.print("<tr><td><br><br><div class=\"button1\"><input class=\"next\" type=submit value=Next></div></td></tr>");
		out.print("</table>");  
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.close();
	}
}