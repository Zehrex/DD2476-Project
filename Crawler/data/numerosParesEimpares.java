2
https://raw.githubusercontent.com/asuescun/Matematicas-Locas/master/numerosParesEimpares.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matematicaslocas;

/**
 *
 * @author USUARIO
 */
public class numerosParesEimpares extends numeros {
    
    public numerosParesEimpares(Double numeroUno, Double numeroDos) {
        super(numeroUno, numeroDos);
    }
    public void mostrarNumerosPares(Double numeroUno, Double numeroDos){
     this.numeroUno=numeroUno;
     this.numeroUno=numeroDos;
           for(double a=numeroUno;numeroUno<=numeroDos;numeroUno++){
               if(numeroUno%2==0){
                 System.out.println("Numero:"+numeroUno);   
               }
           
         }
    }
     public void mostrarNumerosIPares(Double numeroUno, Double numeroDos){
     this.numeroUno=numeroUno;
     this.numeroUno=numeroDos;
           for(double a=numeroUno;numeroUno<=numeroDos;numeroUno++){
               if(numeroUno%2==1){
                 System.out.println("Numero:"+numeroUno);   
               }
           
         }
    }
    }
   

