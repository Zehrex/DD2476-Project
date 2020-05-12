2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Zifuchuan.java
package ray;

public class Zifuchuan {
	public static void main(String[] args)
	{
		String greeting = "hello";   /*取子串*/
		String s = greeting.substring(0, 3);
		System.out.println(s);
		String expletive = "Expensive";    /*字符串拼接*/
		String PG13 = "delete";
		String message = expletive + PG13;
		System.out.println(message);
		greeting = greeting.substring(0, 3) + "p!";    /*修改字符串*/
		System.out.println(greeting);
		System.out.println(s.equals(message));    /*检测字符串是否相等*/ 
		int n = greeting.length();    /*字符串长度（代码单元数量）*/
		System.out.println(n);
		char first = greeting.charAt(0);    /*调用s.charAt(n)返回位置n的代码单元*/
		char last = greeting.charAt(4);
		System.out.println(first);
		System.out.println(last);
	}
}
