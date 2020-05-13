2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/basic/javapractice/EquationOfFloat.java
package com.basic.javapractice;

/**
 * 求解 一元二次方程ax^2+bx+c=0
 */
public class EquationOfFloat {

	public static void main(String[] args) {
		// x*x + 3*x - 4 = 0
		double a = 1.0;
		double b = 3.0;
		double c = -4.0;
		// 求平方根可用 Math.sqrt():
		// double x = Math.sqrt(2)); // ==> 1.414
		// TODO:if判定根数量然后求解
		double d=b * b - 4 * a * c;
		if(d > 0) {
			double r1 = (-b + Math.sqrt(d)) / (2 * a);
			double r2 = (-b - Math.sqrt(d)) / (2 * a);
			System.out.println(r1 + ", " + r2);
			System.out.println(r1 == 1 && r2 == -4 ? "测试通过" : "测试失败");
		}
		else if(d == 0) {
			double r = (-b + Math.sqrt(d)) / (2 * a);
			System.out.println(r);
		}
		else {
			System.out.println("无解");
		}	
	}

}
