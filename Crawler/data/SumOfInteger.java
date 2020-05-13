2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/basic/javapractice/SumOfInteger.java
package com.basic.javapractice;

/**
 * Sum of integers.
 */
public class SumOfInteger {

	public static void main(String[] args) {
		int n = 100;
		// TODO: sum = 1 + 2 + ... + n
		int sum = 0;
		for(int i = 0; i <= n ; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
	
	/*
	 00000000 00000000 00000000 00000000
	 最高位为符号位
	 */

}
