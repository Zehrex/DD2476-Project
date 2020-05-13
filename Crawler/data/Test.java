2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part2/src/com/opp/inherit/Test.java
package com.opp.inherit;

public class Test {//组合
	public static void main(String[] args) {
		Teacher t = new Teacher();
		t.setName("java");
		String name = t.getName();
		System.out.println(name);
	}

}

class Teacher extends Book{

	protected int score;
	protected Book book;
	public String getScore() {
	    	return name;
	}
	
	public void setScore(int score) {
	    	this.score = score;
	    	
	}
}

class Book {
	protected String name;
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
}