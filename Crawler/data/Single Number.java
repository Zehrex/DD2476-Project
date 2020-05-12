2
https://raw.githubusercontent.com/aayush-grover/30-Days-LeetCode-Challenge/master/Single%20Number.java
// Leetcode Challenge #1

// Given a non-empty array of integers, every element appears twice except for one. Find that single one.

// ex 1:

// Input: [2,2,1]
// Output: 1

// ex 2:

// Input: [4,1,2,1,2]
// Output: 4

class Solution {
    public int singleNumber(int[] nums) {        
        int singleNo=0;
         for(int i=0;i<nums.length;i++)
        {
           singleNo^= nums[i]; 
        }
        return singleNo;
        
    }
}