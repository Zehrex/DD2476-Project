21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day10/EvenOddStr.java
package day10;

public class EvenOddStr {
	public static void main(String[] args) {
		// create a method isEven that accepts one String argument
		// if the length of the argument is even return true
		// otherwise return false
		
		System.out.println(isEven("Johns"));
	}
	
	public static boolean isEven(String str) {
		// how do we know if number is even 
		// we can use mod or remainder %
		// number % 2 == 0 -> number is even
		
		int len = str.length();
		if(len % 2 == 0) {
			return true;
		}else {
			return false;
		}
		
		//return len % 2 == 0;
	}
}
