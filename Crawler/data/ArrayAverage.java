2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/array/javapractice/ArrayAverage.java
package com.array.javapractice;

/**
 * 二维数组
 * Arrays.deepToString() -> 多维数组打印
 */
public class ArrayAverage {
	public static void main(String[] args) {
		// 用二维数组表示的学生成绩:
		int[][] scores = { 
				{ 82, 90, 91 }, 
				{ 68, 72, 64 }, 
				{ 95, 91, 89 }, 
				{ 67, 52, 60 }, 
				{ 79, 81, 85 }, 
		};
		// TODO:
		double sum = 0;
		double count = 0;
		for(int n = 0; n < 5; n++) {
			for(int m = 0; m < 3; m++) {
				sum += scores[n][m];
				count++;
			}
		}
		double average = sum / count;
		System.out.println(average);
		if (Math.abs(average - 77.733333) < 0.000001) {
			System.out.println("测试成功");
		} else {
			System.out.println("测试失败");
		}
	}
}
