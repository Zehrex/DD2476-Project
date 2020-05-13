1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/MinimumPathSum.java
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        for(int i = m - 1; i >= 0; i --) {
            for(int j = n - 1; j >= 0; j --) {
                if(i == m - 1 && j == n - 1) {
                    continue;
                } else if(i + 1 >= m) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if(j + 1 >= n) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[0][0];
    }

}
