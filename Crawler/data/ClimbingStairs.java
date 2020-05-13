1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/ClimbingStairs.java
public class ClimbingStairs {

    public int climbStairs(int n) {
        if(n == 1 || n == 2) {
            return n;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;

        for(int i = 2; i < dp.length; i ++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }

}
