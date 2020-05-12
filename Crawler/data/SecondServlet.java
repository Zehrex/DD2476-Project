2
https://raw.githubusercontent.com/SShivani/Online-Quiz-Project/master/WEB-INF/classes/SecondServlet.java
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class SecondServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		String Q1=request.getParameter("Q1");
		Cookie cq1=new Cookie("Q1",Q1);
		response.addCookie(cq1);
		String Q2=request.getParameter("Q2");
		Cookie cq2=new Cookie("Q2",Q2);
		response.addCookie(cq2);
		String Q3=request.getParameter("Q3");
		Cookie cq3=new Cookie("Q3",Q3);
		response.addCookie(cq3);
		String Q4=request.getParameter("Q4");
		Cookie cq4=new Cookie("Q4",Q4);
		response.addCookie(cq4);
		String Q5=request.getParameter("Q5");
		Cookie cq5=new Cookie("Q5",Q5);
		response.addCookie(cq5);

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
		out.print("<form action=result.com method=post>");
		out.print("<div class=\"image\"><img src=\"img.jfif\" width=\"100%\" height=\"30%\"></div>");
		out.print("<h1 align=center>ExQuizMe</h1><br>");
		out.print("<h1 align=center>Quiz On Java</h1>");
		out.print("<table align=center><tr><td>");
		out.print("<br><h3 align=left>Q6. Which of those allows duplicate elements?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q6 value=option1>Set</td></tr>");
		out.print("<tr><td><input type=radio name=Q6 value=option2>List</td></tr>");
		out.print("<tr><td><input type=radio name=Q6 value=option3>All</td></tr>");
		out.print("<tr><td><input type=radio name=Q6 value=option4>None of these</td></tr>");

		out.print("<tr><td><br><h3 align=left>Q7 Which interface should be implemented for sorting on basis of many criteria’s?</h3>");
        out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q7 value=option1>Comparator</td></tr>");
		out.print("<tr><td><input type=radio name=Q7 value=option2>Comparable</td></tr>");
		out.print("<tr><td><input type=radio name=Q7 value=option3>Serializable</td></tr>");
		out.print("<tr><td><input type=radio name=Q7 value=option4>None of the above</td></tr>");

		out.print("<tr><td><br><h3 align=left>Q8. What will be the output of the following code?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><div><img src=\"ques1.jpg\" width=\"60%\" height=\"60%\"></div></td></tr>");
		out.print("<tr><td><input type=radio name=Q8 value=option1>Runtime Exception</td></tr>");
		out.print("<tr><td><input type=radio name=Q8 value=option2>Compiletime Exception</td></tr>");
		out.print("<tr><td><input type=radio name=Q8 value=option3>3</td></tr>");
		out.print("<tr><td><input type=radio name=Q8 value=option4>3.0</td></tr>");

		out.print("<tr><td><br><h3 align=left>Q9. Which of those doesn't have an index based structure?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q9 value=option1>Set</td></tr>");
		out.print("<tr><td><input type=radio name=Q9 value=option2>List</td></tr>");
		out.print("<tr><td><input type=radio name=Q9 value=option3>Map</td></tr>");
		out.print("<tr><td><input type=radio name=Q9 value=option4>All of the above</td></tr>");

		out.print("<tr><td><br><h3 align=left>Q10. Which of the following is a marker interface?</h3>");
		out.print("</td></tr><br>");
		out.print("<tr><td><input type=radio name=Q10 value=option1>Runnable</td></tr>");
		out.print("<tr><td><input type=radio name=Q10 value=option2>Serializable</td></tr>");
		out.print("<tr><td><input type=radio name=Q10 value=option3>Comparable</td></tr>");
		out.print("<tr><td><input type=radio name=Q10 value=option4>None of the above</td></tr>");
        

		out.print("<tr><td><br><br><div class=\"button1\"><input class=\"next\" type=submit value=Submit></div></td></tr>");
		out.print("</table>");  
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");


	}
}