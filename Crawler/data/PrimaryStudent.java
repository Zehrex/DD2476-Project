2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part2/src/com/opp/inherit/PrimaryStudent.java
package com.opp.inherit;

public class PrimaryStudent extends Student {

	protected int grade;
	
	public PrimaryStudent(String name, int age, int score, int grade) {
		super(name, age, score);
		// TODO Auto-generated constructor stub
		this.grade = grade;
	}

}
