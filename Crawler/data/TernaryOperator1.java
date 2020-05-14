21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day12/TernaryOperator1.java
package day12;

public class TernaryOperator1 {
	public static void main(String[] args) {
	    boolean isEven = isEven(4);
		
		String str = isEven ? "Number is even" : "Number is odd";
		
		System.out.println(str); // Number is odd
	}
	
	public static boolean isEven(int number) {
		return number % 2 == 0;
	}
}
