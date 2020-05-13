2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Dashuzhi.java
package ray;
/*假设你被邀请参加抽奖活动，并从490个可能的数值中抽取60个，这个程序将会得到
 * 中彩的概率1/191528501975691732068491783499249034628788241073665059156342839832464942946480
 */
import java.math.BigInteger;
import java.util.Scanner;

public class Dashuzhi {
	public static void main(String[] args)
	{ 
		Scanner in = new Scanner(System.in);
		
		System.out.print("How many numbers do you need to draw?");
		int k = in.nextInt();
		
		System.out.print("What is the highest number you can draw?");
		int n = in.nextInt();
		
		/*
		 * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
		 */
		
		BigInteger lotteryOdds = BigInteger.valueOf(1);
		
		for(int i = 1; i <= k; i++)
			lotteryOdds = ((BigInteger) lotteryOdds).multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
		
		System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
	}

}
