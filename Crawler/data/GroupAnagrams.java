1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/GroupAnagrams.java
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(int i = 0; i < strs.length; i ++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sorted = String.valueOf(chars);
            if(map.containsKey(sorted)) {
                map.get(sorted).add(strs[i]);
            } else {
                map.put(sorted, new LinkedList<>());
                map.get(sorted).add(strs[i]);
            }
        }

        return new LinkedList<>(map.values());
    }

}
