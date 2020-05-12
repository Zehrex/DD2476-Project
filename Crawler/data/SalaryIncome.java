2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part2/src/com/opp/polymorphic/SalaryIncome.java
package com.opp.polymorphic;

public class SalaryIncome extends Income {

	public SalaryIncome(double income) {
		super(income);
	}

	@Override
	public double getTax() {
		if (income <= 5000) {
			return 0;
		}
		return (income - 5000) * 0.2;
	}
}
