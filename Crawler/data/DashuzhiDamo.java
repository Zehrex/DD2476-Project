2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/DashuzhiDamo.java
package ray;

import java.math.BigInteger;

public class DashuzhiDamo {
	public static void main(String[] args)
	{
		/*使用静态的valueOf方法可以将普通的数值转化为大数值*/
		BigInteger a = BigInteger.valueOf(100);
		BigInteger b = BigInteger.valueOf(11);
		/*不能使用+，*，等算数运算符处理大数值，
		 * 而要用大数值类中的add和multiply等方法。
		 */
		BigInteger c = a.add(b);
		BigInteger d = c.multiply(b.add(BigInteger.valueOf(2)));
		System.out.println(c);
		System.out.println(d);
		
	}

}
