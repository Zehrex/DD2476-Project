2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Xunhuan.java
package ray;

import java.util.Scanner;
//计算需要多长时间才能够存储一定数量的退休金
//假定每年存入相同数量的金额，而且利率是固定的
public class Xunhuan {    //while循环
	public static void main(String[] args)
	{
		//read inputs
		Scanner in = new Scanner(System.in);
		
		System.out.print("How much money do you need to retire?");
		double goal = in.nextDouble();
		
		System.out.print("How much money will you conteibute every year?");
		double payment = in.nextDouble();
		
		System.out.print("Interest rate in %: ");
		double interestRate = in.nextDouble();
		
		double balance = 0;
		int years = 0;    //计数器
		
		//update account balance while goal isn't reached
		while(balance < goal)
		{
			//add this year's payment and interest
			balance += payment;
			double interest = balance * interestRate/100;
			balance += interest;
			years += 1;
		}
		
		System.out.println("You can retire in " + years +"years.");
	}

}
