2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/For.java
package ray;
/*这个程序用来计算抽奖中奖概率。例如必须从1~50之间的数字中取6个数字来抽奖
 * ，那么会有（50*49*48*47*46*45）/（1*2*3*4*5*6）种可能的结果，所以中奖的机率为
 * 1/15890700.祝你好运！*/
import java.util.Scanner;

public class For {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.print("How many numbers do you need to draw?");
		int k = in.nextInt();
		
		System.out.print("What is the hignest number you can draw?");
		int n = in.nextInt();
		
		/*compute binomial coefficient n*(n+1)*(n+2)*...*(n-k+1)/(1*2*3*...*k)*/
		
		int lotteryOdds = 1;
		for(int i = 1;i <= k;i++)
			lotteryOdds = lotteryOdds * (n - i + 1)/i;
		
		System.out.println("Your odds are 1 in " + lotteryOdds + ".Good luck!");
	}
}
