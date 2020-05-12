2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/flow/javapractice/FlowWhile.java
package com.flow.javapractice;

/**
 * while练习
 */
public class FlowWhile {

	public static void main(String[] args) {
		int sum = 0;
		int m = 20;
		int n = 100;
		// FIXME: 使用while计算M+...+N:
		while (m<=n) {//满足条件 进入循环 先判断 后执行
			sum = sum + m;
			m++;
		}
		System.out.println(sum);
	}

}
