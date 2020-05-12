2
https://raw.githubusercontent.com/SShivani/Online-Quiz-Project/master/WEB-INF/classes/ResultServlet.java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ResultServlet extends HttpServlet
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		String Q6=request.getParameter("Q6");
		String Q7=request.getParameter("Q7");
		String Q8=request.getParameter("Q8");
		String Q9=request.getParameter("Q9");
		String Q10=request.getParameter("Q10");

		ArrayList<String> r = new ArrayList<String>(4); 
        r.add("option1"); 
        r.add("option2"); 
        r.add("option3"); 
        r.add("option4");
		
		int co=0,at=0;
		if(r.contains(Q6))
			at++;
		if(r.contains(Q7))
			at++;
		if(r.contains(Q8))
			at++;
		if(r.contains(Q9))
			at++;
		if(r.contains(Q10))
			at++;

		Cookie[] c=request.getCookies();

		Map<String,String> hm=new HashMap<String,String>(); 

		hm.put("Q1","option2");
		hm.put("Q2","option1");
		hm.put("Q3","option2");
		hm.put("Q4","option4");
		hm.put("Q5","option3");

		for(int i=0;i<=4;i++)
		{
			if(r.contains(c[i].getValue()))
				at++;
		}

		if(hm.get(c[0].getName()).equals(c[0].getValue()))
			co++;		
		if(hm.get(c[1].getName()).equals(c[1].getValue()))
			co++;
		if(hm.get(c[2].getName()).equals(c[2].getValue()))
			co++;
		if(hm.get(c[3].getName()).equals(c[3].getValue()))
			co++;
		if(hm.get(c[4].getName()).equals(c[4].getValue()))
			co++;

		if(Q6!=null && Q6.equals("option2"))
			co++; 
		if(Q7!=null && Q7.equals("option1"))
			co++;
		if(Q8!=null && Q8.equals("option1"))
			co++;
		if(Q9!=null && Q9.equals("option1"))
			co++;
		if(Q10!=null && Q10.equals("option2"))
			co++;

		out.print("<head><style>");
		out.print("body{padding:0%;margin:0%;}");
		out.print(".box form h1{padding:0%;margin:0%;}");
		out.print(".button1{text-align:center;}");
		out.print(".next{background-color:green;color:white;width:10%;font-size:15px;border-radius:35%;padding:10px;}");
		out.print(".score{color:white;}");
		out.print("");
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
		out.print("<h1 align=center>Quiz On Java</h1><br>");
		out.print("<h1 align=center>=====================</h1><br><br>");
		out.print("<table align=center class=\"score\">");
		out.print("<tr><td><div><h2>Total number of questions</h2></td><td><h2>: 10</h2></td></tr><br>");
    	out.print("<tr><td><div><h2>Number of answered questions</h2></td><td><h2>: "+at+"</h2></td></tr><br>");
		out.print("<tr><td><div><h2>Number of unanswered questions</h2></td><td><h2>: "+(10-at)+" </h2></td></tr><br>");   
		out.print("<tr><td><div><h2>Your Score</h2></td><td><h2>: "+co+"</h2></td></tr><br>");   
		out.print("</table>");
		out.print("</div>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.close();
	}
}
