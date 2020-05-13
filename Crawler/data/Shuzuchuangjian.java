2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Shuzuchuangjian.java
package ray;

import java.util.Arrays;

public class Shuzuchuangjian {
	public static void main(String[] args)
	{
		int[] smallPrimes = {2, 3, 5, 7, 11, 13};    //创建数组对象并同时赋予初始值的简化书写形式
		smallPrimes = new int[] {1, 2, 3, 4, 5};
		int [] anonymous = {17, 19, 23, 29, 31, 37};
		smallPrimes = anonymous;
		System.out.println(Arrays.toString(smallPrimes));
		
		int[] luckyNumbers = smallPrimes;    //数组中元素的拷贝
		luckyNumbers[5] = 12;
		System.out.println(smallPrimes[5]);
		
		int[] copiedluckyNumbers = Arrays.copyOf(luckyNumbers, luckyNumbers.length);    //将一个数组中所有的值拷贝到一个新的数组中
		System.out.println(Arrays.toString(copiedluckyNumbers));
		
		luckyNumbers = Arrays.copyOf(luckyNumbers, 2 * luckyNumbers.length);    //第二个参数是新数组的长度，这个方法通常用来增加数组的大小
		System.out.println(Arrays.toString(luckyNumbers));
		
	}
}
