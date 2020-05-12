2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/CustomerTest.java
package leiTest;

public class CustomerTest {
	public static void main(String[] args)
	{
		Customer c = new Customer();
		c.setId(1001);
		c.setName("Íõè¡");
		c.setAge(18);
		c.setAddr("ÉÂÎ÷ÓÜÁÖ");
		
		System.out.println(c.getId());
		System.out.println(c.getName());
		System.out.println(c.getAge());
		System.out.println(c.getAddr());
	}
}
