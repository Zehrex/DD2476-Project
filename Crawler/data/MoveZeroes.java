1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/MoveZeroes.java
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int partitionIndex = 0;

        for(int i = 0; i < nums.length; i ++) {
            if(nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[partitionIndex];
                nums[partitionIndex] = temp;
                partitionIndex ++;
            }
        }
    }

}
