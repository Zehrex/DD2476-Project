21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day5/Division.java
package day5;

public class Division {
	public static void main(String[] args) {
		int numOne = Integer.parseInt(args[0]);
		int numTwo = Integer.parseInt(args[1]);
		int res = numOne / numTwo;

		System.out.println(numOne + " / " + numTwo + " = " + res);
	}
}