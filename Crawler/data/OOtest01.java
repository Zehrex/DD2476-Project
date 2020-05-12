2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/OOtest01.java
package leiTest;

public class OOtest01 {
	public static void main(String[] args)
	{
		OOtestUser u = new OOtestUser();
		
		System.out.println(u.num);
		System.out.println(u.name);
		System.out.println(u.addr);
		
		u.num = 001;
		u.name = "王琛";
		u.addr = new OOtestAddress();
		//OOtestAddress a = new OOtestAddress();
		//u.addr = a;    此两行与上一行作用相同
		
		System.out.println(u.name + "居住在" + u.addr.city + "市");
		System.out.println(u.addr.city + "市的" + u.addr.street + "街道");
		System.out.println("邮政编码是" + u.addr.zipcode);
		
		u.addr.city = "榆林";
		//a.city = "榆林";    与上行代码作用一样
		u.addr.street = "航宇路";
		u.addr.zipcode = "719200";
		
		System.out.println(u.name + "居住在" + u.addr.city + "城市");
		System.out.println(u.addr.city + "城市的" + u.addr.street + "街道");
		System.out.println("邮政编码是" + u.addr.zipcode);
	}
}
