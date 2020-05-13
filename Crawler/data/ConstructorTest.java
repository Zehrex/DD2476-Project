2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/ConstructorTest.java
package leiTest;

public class ConstructorTest {

	public static void main(String[] args)
	{
		GouzaoqiDemo g1 = new GouzaoqiDemo();
		GouzaoqiDemo g2 = new GouzaoqiDemo(10);
		GouzaoqiDemo g3 = new GouzaoqiDemo("王琛");
		GouzaoqiDemo g4 = new GouzaoqiDemo(10 , "王琛");
		
		//调用带有static的方法:类名.
		ConstructorTest.doSome();    
		doSome();    //在同一个类中可直接调用，效果与上一行代码一样
		
		//调用没有static的方法:引用.
		//doOther方法在ConstructorTest类中，所以要创建ConstructorTest对象
		//创建ConstructorTest对象，调用无参数构造方法。
		ConstructorTest c = new ConstructorTest();    //一个类中没有任何构造方法的话，系统默认提供一个无参数构造器
		c.doOther();
	}
	public static void doSome()
	{
		System.out.println("do some!");
	}
	
	public void doOther()
	{
		System.out.println("do other!");
	}
}
