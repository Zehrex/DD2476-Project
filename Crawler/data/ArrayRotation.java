2
https://raw.githubusercontent.com/njcodernoob/codility-java-solutions/master/ArrayRotation.java
package com.challenges;

/*
 * 
 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).

The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

Write a function:

class Solution { public int[] solution(int[] A, int K); }

that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.

For example, given

    A = [3, 8, 9, 7, 6]
    K = 3
the function should return [9, 7, 6, 3, 8]. Three rotations were made:

    [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
    [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
    [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
For another example, given

    A = [0, 0, 0]
    K = 1
the function should return [0, 0, 0]

Given

    A = [1, 2, 3, 4]
    K = 4
the function should return [1, 2, 3, 4]

Assume that:

N and K are integers within the range [0..100];
each element of array A is an integer within the range [−1,000..1,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */

public class ArrayRotation {
	
	public static int[] solution(int[] a, int k) {
        // write your code in Java SE 8
        
		
		if (k == 0 || a.length == 0) return a;
        
        int len = a.length;
        if (len < k) {
            k = k % len;
        }
        
        rotate(a, a.length - k, a.length - 1);
        rotate(a, 0, a.length - k - 1);
        rotate(a, 0, a.length - 1);
        
        return a;
    }
    
    private static void rotate(int[] a, int start, int end) {
        
        while (start < end) {
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            
            start++;
            end--;
        }
    
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 3, 8, 9, 7, 6 };
		System.out.println(solution(array, 3));

	}

}
