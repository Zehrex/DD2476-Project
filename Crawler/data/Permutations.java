1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/Permutations.java
import java.util.LinkedList;
import java.util.List;

public class Permutations {

    private List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        permute(nums, 0, new LinkedList<>());
        return ans;
    }

    private void permute(int[] nums, int index, LinkedList<Integer> curr) {
        if(index == nums.length) {
            ans.add(new LinkedList<>(curr));
            return;
        }

        for(int i = 0; i <= curr.size(); i ++) {
            curr.add(i, nums[index]);
            permute(nums, index + 1, curr);
            curr.remove(i);
        }
    }

}
