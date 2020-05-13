2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Diguidemo.java
package ray;

import java.util.Scanner;

public class Diguidemo {    //µÝ¹éÇó1µ½nµÄºÍ
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int retValue = sum(n);
		System.out.println(retValue);
	}
	
	public static int sum(int n) {
		if (n == 1)
			return 1;
	return n + sum(n-1);
	}
}
