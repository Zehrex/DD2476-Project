2
https://raw.githubusercontent.com/asuescun/Matematicas-Locas/master/numeros.java
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
public class numeros {
    Double numeroUno;
    Double numeroDos;

    public numeros(Double numeroUno, Double numeroDos) {
        this.numeroUno = numeroUno;
        this.numeroDos = numeroDos;
    }

    public Double getNumeroUno() {
        return numeroUno;
    }

    public void setNumeroUno(Double numeroUno) {
        this.numeroUno = numeroUno;
    }

    public Double getNumeroDos() {
        return numeroDos;
    }

    public void setNumeroDos(Double numeroDos) {
        this.numeroDos = numeroDos;
    }

    @Override
    public String toString() {
        return "numeros{" + "numeroUno=" + numeroUno + ", numeroDos=" + numeroDos + '}';
    }
    
    
    
}
