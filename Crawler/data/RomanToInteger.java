1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/RomanToInteger.java
import java.util.HashMap;

public class RomanToInteger {

    public static int romanToInt(String s) {
        if(s.length() == 0)
            return 0;

        HashMap<String, Integer> nums = new HashMap<>();

        nums.put("I", 1);
        nums.put("V", 5);
        nums.put("X", 10);
        nums.put("L", 50);
        nums.put("C", 100);
        nums.put("D", 500);
        nums.put("M", 1000);

        char[] chars = s.toCharArray();
        int sum = nums.get(Character.toString(chars[0]));

        for(int i = 1; i < chars.length; i ++) {
            if(nums.get(Character.toString(chars[i])) <= nums.get(Character.toString(chars[i - 1])))
                sum += nums.get(Character.toString(chars[i]));

            else
                sum += nums.get(Character.toString(chars[i])) - nums.get(Character.toString(chars[i - 1])) - nums.get(Character.toString(chars[i - 1]));

        }

        return sum;
    }

}
