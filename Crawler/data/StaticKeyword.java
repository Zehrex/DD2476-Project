21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day9/StaticKeyword.java
package day9;

public class StaticKeyword {
	public static void main(String[] args) {
		
		School objOne = new School();
		objOne.id = 1;
		objOne.name = "Shool #13";
		objOne.year = 2020;
		printDetails(objOne);
		
		
		School objTwo = new School();
		printDetails(objTwo);
		
		//printDetails(objOne);
		
	}
	
	public static void printDetails(School school) {
		System.out.println("School id: " + school.id);
		System.out.println("Name: " + school.name);
		System.out.println("Year: " + school.year);
		System.out.println("------------");
	}
}
