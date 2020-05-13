1
https://raw.githubusercontent.com/FabianCristancho/Grammatical-Tree-LF/master/tst/tests/GramaticalTest.java
package tests;

import java.util.ArrayList;
import java.util.Scanner;

public class GramaticalTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese los simbolos terminales separados por coma");
		String terminals = sc.nextLine();
		System.out.println("Ingrese los simbolos no terminales separados por coma");
		String nonTerminals = sc.nextLine();

		System.out.println(
				"Ingrese las producciones en formato NT-s1 o bien NT-s1|s2|s3... \ndonde NT representa al simbolo no terminal, y sn representa a las prodcciones respectivas");
		System.out.println("cuando termine de ingresar las producciones, por favor digite el caracter 0");
		ArrayList<String> productions = new ArrayList<String>();
		String production;
		do {
			production = sc.nextLine();
			productions.add(production);
		} while (!production.equals("0"));
		
		char initNT = productions.get(0).split("-")[0].charAt(0);
		String firstIteration = findProduction(initNT, productions);
		
		System.out.println(findProduction(initNT, productions));
		

		System.out.println("Proceso termina con éxito");
	}

	private static boolean productionHasBifurcation(String production) {
		for (int i = 0; i < production.length(); i++) {
			if (production.charAt(i) == '|')
				return true;
		}
		return false;
	}

	private static String findProduction(char nt, ArrayList<String> productions) {
		int i = 0;
		String result = "";
		while(i<productions.size() && nt != productions.get(i).split("-")[0].charAt(0))
			i++;
		
		if(nt == productions.get(i).split("-")[0].charAt(0)) {
			String production = productions.get(i).split("-")[1];
			if(productionHasBifurcation(production)) {
				String[] symbols = production.split("[|]"); 
				for (int j = 0; j < symbols.length; j++) {
					result += symbols[j];
					if (j < symbols.length - 1)
						result += ",";
				}
			}else {
				result = production;
			}
		}else {
			System.out.println("Símbolo no terminal inválido");
		}
		return result;
	}

}
