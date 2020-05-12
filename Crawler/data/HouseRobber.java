1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/HouseRobber.java
public class HouseRobber {

    public static int rob(int[] arr) {
        if(arr.length == 0) {
            return 0;
        } else if(arr.length == 1) {
            return arr[0];
        } else if(arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i = 2; i < dp.length; i ++) {
            dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }

}
