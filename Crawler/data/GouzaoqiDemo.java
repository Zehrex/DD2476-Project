2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/GouzaoqiDemo.java
package leiTest;
//构造方法支持重载机制。在一个类中可以编写多个方法
//这多个构造方法显然已经构成方法重载机制
public class GouzaoqiDemo {    //构造器方法
	public GouzaoqiDemo()    //无参数的构造方法
	{
		System.out.println("hellow");
	}
	
	public GouzaoqiDemo(int i)    //有参数的构造方法
	{
		System.out.println("带有int类型参数的构造器");
	}
	
	public GouzaoqiDemo(String name)    //有参数的构造方法
	{
		System.out.println("带有String类型参数的构造器");
	}
	
	public GouzaoqiDemo(int i , String name)    //有参数的构造方法
	{
		System.out.println("带有int和String类型参数的构造器");
	}
}
