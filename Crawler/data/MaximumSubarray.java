1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/MaximumSubarray.java
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }

    private int maxSubArray(int[] nums, int low, int high) {
        if(low >= high) {
            return nums[low];
        }

        int mid = low + (high - low) / 2;

        int maxCross = maxCross(nums, low, high);
        int maxLeft = maxSubArray(nums, low, mid);
        int maxRight = maxSubArray(nums, mid + 1, high);

        return Math.max(Math.max(maxLeft, maxRight), maxCross);
    }

    private int maxCross(int[] nums, int low, int high) {
        int mid = (low + (high - low) / 2);

        int leftSum = 0, leftMaxSum = Integer.MIN_VALUE;

        for(int i = mid; i >= low; i --) {
            leftSum += nums[i];
            leftMaxSum = Math.max(leftMaxSum, leftSum);
        }

        int rightSum = 0, rightMaxSum = Integer.MIN_VALUE;

        for(int j = mid + 1; j <= high; j ++) {
            rightSum += nums[j];
            rightMaxSum = Math.max(rightMaxSum, rightSum);
        }

        return leftMaxSum + rightMaxSum;
    }

}
