2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/Duixiang.java
package leiTest;

public class Duixiang {

	public static void main(String[] args)
	{
		Duixiangdemo s = new Duixiangdemo();  //使用自定义类,s为引用的局部变量
		
		/*访问实例变量的语法格式
		  读取数据：引用.变量名；
		  修改数据：引用.变量名 = 值；*/
		
		//读取数据
		int n = s.num;
		int a = s.age;
		String na = s.name;
		String ad = s.addr;
		
		System.out.println("学号 = " + n);
		System.out.println("名字 = " + na);
		System.out.println("年龄 = " + a);
		System.out.println("地址 = " + ad);
		
		System.out.println("学号 = " + s.num);
		System.out.println("名字 = " + s.name);
		System.out.println("年龄 = " + s.age);
		System.out.println("地址 = " + s.addr);
		
		//修改数据
		s.num = 001;
		s.name = "王琛";
		s.age = 19;
		s.addr = "榆林";
		
		System.out.println("学号 = " + s.num);
		System.out.println("名字 = " + s.name);
		System.out.println("年龄 = " + s.age);
		System.out.println("地址 = " + s.addr);
	}
}
