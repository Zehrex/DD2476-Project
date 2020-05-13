2
https://raw.githubusercontent.com/asuescun/Matematicas-Locas/master/rangoNumeros.java
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
public class rangoNumeros extends numeros {
    
    public rangoNumeros(Double numeroUno, Double numeroDos) {
        super(numeroUno, numeroDos);
    }
    public void mostrarNumeros(Double numeroUno, Double numeroDos){
     this.numeroUno=numeroUno;
     this.numeroUno=numeroDos;
           for(double a=numeroUno;numeroUno<=numeroDos;numeroUno++){
             System.out.println("Numero:"+numeroUno);
         }
    }
    
}
