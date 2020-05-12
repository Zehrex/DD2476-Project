2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/flow/javapractice/FlowDoWhile.java
package com.flow.javapractice;

/**
 * while练习
 */
public class FlowDoWhile {

	public static void main(String[] args) {
		int sum = 0;
		int m = 20;
		int n = 100;
		// FIXME: 使用do while计算M+...+N:
		do {//先执行一次 再进行判定
			sum = sum + m;
			m++;
		} while (m<=n);
		System.out.println(sum);
	}

}
