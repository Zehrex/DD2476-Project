1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/LongestCommonPrefix.java
import java.util.Stack;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";

        String prefix = "";
        char[] chars = strs[0].toCharArray();
        Stack<String> letters = new Stack<>();

        for(int i = chars.length - 1; i >= 0; i --){
            letters.push(String.valueOf(chars[i]));
        }

        while(true){
            if(letters.isEmpty())
                return prefix;
            prefix += letters.pop();
            for(int i = 1; i < strs.length; i ++){
                if(strs[i].indexOf(prefix) != 0){
                    return prefix.substring(0, prefix.length() - 1);
                }
            }
        }
    }

}
