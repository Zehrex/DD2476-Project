21
https://raw.githubusercontent.com/Suranchiyev/java-sdet-2020/master/src/day19/WarmUp1.java
package day19;

public class WarmUp1 {
	public static void main(String[] args) {
		//            012345
		String str = "Status";
		
		String strRev = "";
		 //                          -1 >= 0
		for(int i = str.length() - 1; i >= 0 ; i--) {
			//sutatS
			strRev = strRev + str.charAt(i);
		}
		
		System.out.println(strRev);
	}
}
