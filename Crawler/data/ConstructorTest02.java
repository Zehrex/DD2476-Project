2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/ConstructorTest02.java
package leiTest;

public class ConstructorTest02 {

	public static void main(String[] args) {
		
		//创建对象
		 //当一个类中元素过多，想快速查看，在当前类中使用CTRL + o 快捷键，然后输入要查找的元素名称。
		 Account act1 = new Account();
		 
		 System.out.println("账号: " + act1.getActno());
		 System.out.println("余额: " + act1.getBalance());
		 
		 Account act2 = new Account("222");
		 
		 System.out.println("账号: " + act2.getActno());
		 System.out.println("余额: " + act2.getBalance());
		 
		 Account act3 = new Account(33.33);
		 
		 System.out.println("账号: " + act3.getActno());
		 System.out.println("余额: " + act3.getBalance());
		 
		 Account act4 = new Account("444" , 44.44);
		 
		 System.out.println("账号: " + act4.getActno());
		 System.out.println("余额: " + act4.getBalance());
	}

}
