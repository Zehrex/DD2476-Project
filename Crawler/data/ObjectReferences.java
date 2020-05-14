21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day17/ObjectReferences.java
package day17;

public class ObjectReferences {
	public static void main(String[] args) {
		StringBuilder sbA = new StringBuilder("abc");
		StringBuilder sbB = sbA;
		
		sbB.append("d");	
		System.out.println("sbA: " + sbA); // abcd
		System.out.println("sbB: " + sbB); // abcd
		
		sbA.append("ef");
		System.out.println("sbA: " + sbA); // abcdef
		System.out.println("sbB: " + sbB); // abcdef
	}
}
