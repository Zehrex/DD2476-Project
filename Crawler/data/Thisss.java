2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Thisss.java
package ray;

public class Thisss {

	String name;    //实例变量：必须采用“引用.”的方式访问
	
	public Thisss()
	{
		
	}
	
	public void shopping() {
		System.out.println(this.name + "在购物！");
		//当使用s1去访问对象的时候,整个过程中的this就s1.
	}
}
