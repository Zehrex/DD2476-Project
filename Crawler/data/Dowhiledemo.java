2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Dowhiledemo.java
package ray;

import java.util.Scanner;

public class Dowhiledemo {

	public static void main(String[] args)
	{
		int x = 0;
		Scanner input=new Scanner(System.in);
		String str=input.next();
		do
		{
			System.out.println("NB!");
			x +=1;
			if(x >= 10) break;
		}while(str.equals("n"));
	}
}
