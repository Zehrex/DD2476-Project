21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day20/Assignmnet1.java
package day20;

public class Assignmnet1 {
	public static void main(String[] args) {
		char[] arr = {'a', 'b', 'c'};
		
		String str = "";
		
		for(char ch : arr) {
			str = str + ch;
		}
		
		System.out.println(str); // "abc"
		
	}
}
