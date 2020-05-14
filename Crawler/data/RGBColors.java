21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day12/RGBColors.java
package day12;

public class RGBColors {
	public static void main(String[] args) {
		String color = "black";
		String hexNumber;
		//     black
		switch(color) {
		case "white":
			System.out.println("Getting RGB hex number for white");
			hexNumber = "FFFFFF";
			break;
		case "red":
			System.out.println("Getting RGB hex number for red");
			hexNumber = "FF0000";
			break;
		default:
			System.out.println("Color is not found");
			hexNumber = "";
			break;
		}
		
		System.out.println(hexNumber);
	}
}
