2
https://raw.githubusercontent.com/raulmel1o/epIP/master/ep1/CaixaEletronico.java
/*********************************************************************/
/**   ACH2001 - Introdução à Programação                            **/
/**   EACH-USP - Primeiro Semestre de 2020                          **/
/**   2020104 - Norton Trevisan Roman                               **/
/**                                                                 **/
/**   Primeiro Exercício-Programa                                   **/
/**                                                                 **/
/**   Raul Mello Silva                            11870986          **/
/**                                                                 **/
/**   31/03/2020                                                    **/
/*********************************************************************/

/*
	Caixa eletrônico das Ilhas Weblands, com estoque ilimitado de cédulas
	de B$50,00, B$10,00, B$5,00 e B$1,00.
*/
public class CaixaEletronico {
	// Número de cédulas de B$50,00
	static int n50;
	
	// Número de cédulas de B$10,00
	static int n10;

	// Número de cédulas de B$5,00
	static int n5;
	
	// Número de cédulas de B$1,00
	static int n1;


	/*
		Determina a quantidade de cada nota necessária para totalizar
		um determinado valor de retirada, de modo a minimizar a quantidade
		de cédulas entregues.
		
		Abastece as variáveis globais n50,n10, n5 e n1 com seu respectivo
		número de cédulas.
		
		Parâmetro:
			valor - O valor a ser retirado
	*/
	static void fazRetirada(int valor) {
		
		//Testa se o valor é válido, isto é, se é maior que 0
		if (valor >= 0) {
			
			/*
			Divide o valor de retirada pelo valor da cédula para saber quantas cédulas serão necessárias,
			armazena, e utiliza o resto da operação anterior para realizar a próxima divisão.
			
			Ex: 127 
			127 / 50 == 2
			127 % 50 == 27 
			27 / 10 == 2
			27 % 10 == 7
			7 / 5 == 1
			7 % 5 == 2
			2 / 1 == 2
			2 % 1 == 0
			*/

			n50 = (valor / 50);
			int resto50 = (valor % 50);

			n10 = (resto50 /10);
			int resto10 = (resto50 % 10);

			n5 = (resto10 / 5);
			int resto5 = (resto10 % 5);

			n1 = (resto5);

        } else {

			n50 = -1;
            n10 = -1;
            n5 = -1;
			n1 = -1;

		}
	}
	
	/*
		Apenas para seus testes. ISSO SERÁ IGNORADO NA CORREÇÃO
	*/
	public static void main(String[] args) {
		// Exemplo de teste:
		fazRetirada(0);
		System.out.println("Notas de 50: "+n50);
		System.out.println("Notas de 10: "+n10);
		System.out.println("Notas de 5:  "+n5);
		System.out.println("Notas de 1:  "+n1);
	}
}