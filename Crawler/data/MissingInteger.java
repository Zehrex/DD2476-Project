2
https://raw.githubusercontent.com/njcodernoob/codility-java-solutions/master/MissingInteger.java
package com.challenges;

import java.util.HashSet;
import java.util.Set;

/*
 * This is a demo task.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 * 
 * 
 */

public class MissingInteger {
	
	public int solution(int[] a) {
        // write your code in Java SE 8
        
		int max = 0;

        Set<Integer> positives = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) positives.add(a[i]);
            if (a[i] > max) max = a[i];
        }
        
        if (max == 0) return 1;
        
        boolean [] visits = new boolean[max + 1];
        
        for (Integer num : positives) {
            visits[num] = true;
        }
        
        for (int i = 1; i <= max; i++) {
            if (visits[i] == false) return i;
        }
        return max + 1;
    }

}
