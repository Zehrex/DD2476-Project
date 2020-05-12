2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part2/src/com/opp/polymorphic/Income.java
package com.opp.polymorphic;

public class Income {

	protected double income;

	public Income(double income) {
		this.income = income;
	}

	public double getTax() {
		return income * 0.1; // 税率10%
	}

}
