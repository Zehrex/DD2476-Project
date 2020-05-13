1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/SortColors.java
public class SortColors {

    public void sortColors(int[] nums) {
        int[] colors = new int[3];

        for(int i = 0; i < nums.length; i ++) {
            colors[nums[i]] ++;
        }

        int k = 0;

        while(colors[0] > 0) {
            nums[k ++] = 0;
            colors[0] --;
        }

        while(colors[1] > 0) {
            nums[k ++] = 1;
            colors[1] --;
        }

        while(colors[2] > 0) {
            nums[k ++] = 2;
            colors[2] --;
        }
    }

}
