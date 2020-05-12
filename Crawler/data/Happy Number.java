2
https://raw.githubusercontent.com/aayush-grover/30-Days-LeetCode-Challenge/master/Happy%20Number.java
//import java.util.*;


//Write an algorithm to determine if a number n is "happy".

// A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for 
//which this process ends in 1 are happy numbers.
// Return True if n is a happy number, and False if not.


//ex 1:

// Input: 19
// Output: true
// Explanation: 
// 12 + 92 = 82
// 82 + 22 = 68
// 62 + 82 = 100
// 12 + 02 + 02 = 1

class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> h=new HashSet<>();
        int flag=0;
        while(!h.contains(n))
        {
            h.add(n);
            int res=0;
            while(n>0)
            {
                int temp=n%10;
                res+=Math.pow(temp,2);
                n=n/10;
            }
            if(res == 1)
            {
                return true;
            }
            n=res;
        }
        return false;
    }
}