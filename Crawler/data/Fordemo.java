2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Fordemo.java
package ray;
/*从n个数字中抽取k个数字，可以使用以下循环得到结果
 * n*(n-1)*(n-2)*...*(n-k+1)/1*2*3*...*k
 * */
public class Fordemo {
	public static void main(String[] args)
	{
		int a = 1;
		int k = 3;
		int n = 5;
		for(int i = 1;i <= k;i++)
			a = a * (n - i +1) / i;
		System.out.println(a);
	}
}
