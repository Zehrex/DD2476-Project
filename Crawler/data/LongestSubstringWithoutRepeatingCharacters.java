1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/LongestSubstringWithoutRepeatingCharacters.java
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        Map<Character, Integer> indices = new HashMap<>();

        for (int j = 0, i = 0; j < s.length(); j ++) {
            if (indices.containsKey(s.charAt(j))) {
                i = Math.max(indices.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            indices.put(s.charAt(j), j + 1);
        }

        return ans;
    }

}
