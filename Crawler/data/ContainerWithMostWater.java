1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/ContainerWithMostWater.java
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int max = -1;

        for(int i = 0, j = height.length - 1; i < j; ) {
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));

            if(height[i] < height[j]) {
                i ++;
            } else {
                j --;
            }
        }

        return max;
    }

}
