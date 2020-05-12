2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part2/src/com/opp/polymorphic/RoyaltyIncome.java
package com.opp.polymorphic;

/**
 * 稿费收入税率是20%
 */
public class RoyaltyIncome extends Income{

	public RoyaltyIncome(double income) {
		super(income);
		// TODO Auto-generated constructor stub
	}

	// TODO
	public double getTax() {
		return income * 0.2;
	}
}
