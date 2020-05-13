1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/EditDistance.java
public class EditDistance {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for(int i = 0; i < dp.length; i ++) {
            for(int j = 0; j < dp[0].length; j ++) {
                if(i == 0) {
                    dp[0][j] = j;
                } else if(j == 0) {
                    dp[i][0] = i;
                } else if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

}
