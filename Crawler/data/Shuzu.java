2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Shuzu.java
package ray;

import java.util.Arrays;

public class Shuzu {
	public static void main(String[] args)
	{
		int[] a = new int[10];
		for(int i = 0; i < 10; i++)
			a[i] = i;
		System.out.println(Arrays.toString(a));    
		/*数组输出的时候用Arrays类中的toString方法效果更佳
		 * 且可以避免出现[I@50cbc42f的情况（此为数组第一元素的地址）
		 * */
		
		for(int i = 0; i < a.length; i++)    /*循环a中的每一个元素*/
			System.out.println(a[i]);
		System.out.print("\n");
		
		for(int element : a)    /*for each循环，循环a中的每一个元素
		                          优点是不用为下标的起始值和终止值操心，不易出错*/
			System.out.println(element);
	}

}
