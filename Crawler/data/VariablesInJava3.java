21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day3/VariablesInJava3.java
package day3;

public class VariablesInJava3 {
	public static void main(String args[]) {
        String str = "book";
		System.out.println(str);

		str = "phone";
		System.out.println(str);

		// "phone - iphone"
		str = str + " - iphone";
		System.out.println(str);

		str = str +" "+ str;
		System.out.println(str);
	}
}