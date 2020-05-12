1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/LongestPalindromicSubstring.java
public class LongestPalindromicSubstring {

    private int i;

    public String longestPalindrome(String s) {
        String max = "";

        for(i = 0; i < s.length(); i ++) {
            String curr = getMax(s, i);

            if(curr.length() > max.length()) {
                max = curr;
            }
        }

        return max;
    }

    private String getMax(String s, int start) {
        if(start < 0 || start >= s.length()) {
            return "";
        }

        int left, right;
        for(left = start - 1; left >= 0 && s.charAt(left) == s.charAt(start); left --);
        for(right = start + 1; right < s.length() && s.charAt(right) == s.charAt(start); right ++);
        for(left ++, right --, i = right; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); left --, right ++);
        left ++;
        right --;

        return s.substring(left, right + 1);
    }

}
