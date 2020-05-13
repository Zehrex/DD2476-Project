2
https://raw.githubusercontent.com/asuescun/Matematicas-Locas/master/operacionesBasicas.java
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
public class operacionesBasicas extends numeros {
    
    public operacionesBasicas(Double numeroUno, Double numeroDos) {
        super(numeroUno, numeroDos);
    }
    
    public Double suma(Double numeroUno, Double numeroDos){
    this.numeroUno=numeroUno;    
    this.numeroUno=numeroDos;
     System.out.print(numeroUno+"+"+numeroDos+"=");
    return  numeroUno+numeroDos;
    }
    public double resta(Double numeroUno, Double numeroDos){
      System.out.print(numeroUno+"-"+numeroDos+"=");
    return  numeroUno-numeroDos;
    }
    public double multiplicacion(Double numeroUno, Double numeroDos){
        System.out.print(numeroUno+"*"+numeroDos+"=");
        return  numeroUno*numeroDos; 
    }
    public double division(Double numeroUno, Double numeroDos){
        if(numeroDos==0){
            System.out.println("no se puede dividir entre cero");
        }else{
     System.out.print(numeroUno+"/"+numeroDos+"=");
     return  numeroUno/numeroDos;
}
        return 0;
    }}
