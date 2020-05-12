2
https://raw.githubusercontent.com/asuescun/Matematicas-Locas/master/MatematicasLocas.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matematicaslocas;

import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class MatematicasLocas {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("fallthrough")
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenidos a matematicas locas");
        JOptionPane.showMessageDialog(null, "Por favor ingresa tus datos");
        String nombre = JOptionPane.showInputDialog("Nombre");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("edad"));;
        registoUsuario niño = new registoUsuario(nombre, edad);
        System.out.println(niño.toString());
        JOptionPane.showMessageDialog(null, "para comenzar vamos a ingresar 2 numeros");
        double uno = Double.parseDouble(JOptionPane.showInputDialog("Escribe el primer numero"));
        double dos = Double.parseDouble(JOptionPane.showInputDialog("Escribe el segundo numero numero"));
        for(int i=0;i<=4;i++){
            
        
        int menu = Integer.parseInt(JOptionPane.showInputDialog("MENU\nIngrese el número correspondiente para \n 1.Rngo numeros\n 2.Operaciones basicas \n 3.numeros pares o impares"));
        switch (menu) {
            case 1:
                JOptionPane.showMessageDialog(null, "Conocer los numeros ejemplo escibiste 10 y 20\n te mostraremos los numeros que se encuentren en ese rango\n 11,12,13,14,15,16,17,18,19,20");
                rangoNumeros n = new rangoNumeros(uno, dos);
                n.mostrarNumeros(uno, dos);
                break;
            case 2:
                operacionesBasicas y = new operacionesBasicas(uno, dos);
                int opcion = Integer.parseInt(JOptionPane.showInputDialog("MENU\nElige la opcion que quieras \n 1.sumar\n 2.restar \n 3multiplicar\n dividir"));
                switch (opcion) {
                    case 1:
                        System.out.println(y.suma(uno, dos));
                        break;
                    case 2:
                        System.out.println(y.resta(uno, dos));
                        break;
                    case 3:
                        System.out.println(y.multiplicacion(uno, dos));
                        break;
                    case 4:
                        System.out.println(y.division(uno, dos));
                        break;
                      

                }
                break;
            case 3:
                numerosParesEimpares x = new numerosParesEimpares(uno, dos);
                int opc = Integer.parseInt(JOptionPane.showInputDialog("MENU\nElige la opcion que quieras \n 1.Conocer numeros Pares\n 2.Conocer numeros impares"));
                switch (opc) {
                    case 1:
                        System.out.println("numeros pares");
                        x.mostrarNumerosPares(uno, dos);
                        break;
                    case 2:
                        System.out.println("mostar impares");
                        x.mostrarNumerosIPares(uno, dos);
                        break;

                }
        }
        }
    }
}
