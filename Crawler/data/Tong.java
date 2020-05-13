2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/paixu/Tong.java
package paixu;
/*
 * -桶排序：
 *     -题目：有5名同学考完试，分别考了5分、3分、5分、2分和、8分，（满分10分）
 *         -接下来从小到大进行排序。
 */
import java.util.Scanner;

public class Tong {

	public static void main(String[] args) {
		int[] a = new int[11];
		int i,j;
		for (i = 0 ; i  < 10 ; i ++)
		{
			a[i] = 0;    //将十个数组元素都初始化为0
		}
		
		for (i = 0 ; i < 5 ; i ++)    //循环读入5个数
		{
			Scanner in = new Scanner(System.in);    //从键盘输入考生的分数
			int t = in.nextInt();    //换行输入，输入五位同学的考试成绩
			a[t] ++;   //进行计数
		}
		
		for (i = 0 ; i < 10 ; i ++)    //依次判断a[0]~a[10]
		{
			for (j = 1 ; j <= a[i] ; j ++)    //出现了几次就输出几次
			{
				System.out.print(i);
			}
		}
	}

}
