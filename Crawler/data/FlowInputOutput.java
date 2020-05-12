2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/flow/javapractice/FlowInputOutput.java
package com.flow.javapractice;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * 输入上次考试成绩（int）和本次考试成绩（int），然后输出成绩提高的百分比，保留两位小数位（例如，21.75%）
 */
public class FlowInputOutput {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		System.out.println("输入上次考试成绩");
		int prev = scanner.nextInt();
		System.out.println("输入本次考试成绩");
		int score = scanner.nextInt();
		//数值格式化对象
		//NumberFormat numberFormat = NumberFormat.getInstance();
		//numberFormat.setMaximumFractionDigits(2);
		//String percent = numberFormat.format( 100 * (score - prev) / prev);
		
		double percent = 100 * (score - prev) / prev;
		//DecimalFormat的format方法
		//DecimalFormat df = new DecimalFormat("#.00");
		//System.out.println("成绩提高了" + df.format(percent) + "%");
		System.out.println("成绩提高了" + String.format("%.2f", percent) + "%");
	}

}
