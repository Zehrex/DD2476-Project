2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Inputdemo.java
package ray;

import java.util.Scanner;

public class Inputdemo {
	public static void main(String[] args) {
		//创建Scanner对象，接受从控制台输入
		Scanner input=new Scanner(System.in);
		//接受String类型
		String str=input.next();
		//输出结果
		System.out.println(str);
	}
}