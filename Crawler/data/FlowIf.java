2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/flow/javapractice/FlowIf.java
package com.flow.javapractice;

import java.util.Scanner;

/**
 * 计算BMI
 */
public class FlowIf {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Height (m): ");
		double height = scanner.nextDouble();
		System.out.print("Weight (kg): ");
		double weight = scanner.nextDouble();
		// FIXME:
		double bmi = weight / (height * height);
		// TODO: 打印BMI值及结果
		/*
		 * 过轻：低于18.5
		 * 正常：18.5-25
		 * 过重：25-28
		 * 肥胖：28-32
		 * 非常肥胖：高于32
		 */
		if(bmi < 18.5) {
			System.out.print("过轻");
		}else if(bmi < 25) {
			System.out.print("正常");
		}else if(bmi < 28) {
			System.out.print("过重");
		}else if(bmi < 32) {
			System.out.print("肥胖");
		}else {
			System.out.print("非常肥胖");
		}
	}

}
