2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/array/javapractice/ArrayLoop.java
package com.array.javapractice;

import java.util.Arrays;

/**
 * 遍历数组
 */
public class ArrayLoop {
	public static void main(String[] args) {
		int[] ns = { 1, 4, 9, 16, 25 };
		//System.out.println(Arrays.toString(ns));
		// 倒序打印数组元素:
		for (int i = 4; i >= 0; i--) {
			System.out.println(ns[i]);
		}
	}
}
