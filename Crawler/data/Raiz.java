2
https://raw.githubusercontent.com/raulmel1o/epIP/master/ep2/Raiz.java
/*********************************************************************/
/**   ACH2001 - Introdução à Programação                            **/
/**   EACH-USP - Primeiro Semestre de 2020                          **/
/**   <2020104> - <Norton Trevisan Roman>                           **/
/**                                                                 **/
/**   Segundo Exercício-Programa                                    **/
/**                                                                 **/
/**   <Raul Mello Silva>                        <11870986>          **/
/**                                                                 **/
/**   <02/05/2020>                                                  **/
/*********************************************************************/

/*
	Cálculo para raiz quadrada
*/
public class Raiz {
    /*
        Calcula a raiz quadrada de um valor, com uma determinada
        precisão. Retorna esse valor, ou -1 quando:

        * a < 0
        * epsilon <= 0
        * epsilon >= 1

        Parâmetro:
            a - valor cuja raiz quadrada será calculada
            epsilon - precisão do cálculo
    */
    static double raizQuadrada(double a, double epsilon) {
        // escreva seu código aqui

        /*
            controle if-else faz a verificação das entradas
            entrada invalida retorna codigo de erro -1
            entrada valida retorna o resultado da raiz quadrada
         */
        if (a < 0 || epsilon <= 0 || epsilon >= 1) {
            return -1;
        } else if (a == 0) {
            return 0;
        } else {
            // declara e inicia as variaveis necessarias para a execucao da formula
            double x0 = a / 2;
            double x1 = 0;
            double diferenca = 0;

            // laco do-while
            // repete o processo enquanto a variavel diferenca for maior que o epsilon fornecido
            do {
                x1 = (x0 + a / x0) / 2;

                diferenca = x1 - x0;

                // controlador if fornece o valor absoluto da variavel diferenca
                if (diferenca < 0) {
                    diferenca *= -1;
                }

                x0 = x1;

            } while (diferenca >= epsilon);

            return x1;
        }
    }


    /*
        Apenas para seus testes. ISSO SERÁ IGNORADO NA CORREÇÃO
    */
    public static void main(String[] args) {
        // escreva seu código (para testes) aqui

        // Exemplo de teste:
        double valor = 1;
        double precisao = 0.00000001;
        System.out.println("Raiz de : "+valor+" = "+raizQuadrada(valor, precisao));
    }
}