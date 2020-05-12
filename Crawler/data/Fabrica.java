2
https://raw.githubusercontent.com/guimainini/Practica_Lab3_Parcial2019/master/src/Fabrica.java
import java.util.ArrayList;

public class Fabrica {
    private int id;
    private String nombre;
    private String direccion;
    private ArrayList<Cerveza> listacerveza;

    public Fabrica(int id, String nombre, String direccion, ArrayList<Cerveza> listacerveza) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listacerveza = listacerveza;
    }
    //esta metodo, primero si fija si es nula y despues recorre la lista para ver si esta repetido el nombre
    public void agregarCerveza(Cerveza cerveza) {
            if (listacerveza.size() == 0){
                listacerveza.add(cerveza);
            }else{
                boolean estaOnoesta = false;
                for (int i = 0; i < listacerveza.size(); i++){
                    if (listacerveza.get(i).getNombre().equals(cerveza.getNombre())){
                        estaOnoesta = true;
                    }
                }
                if (!estaOnoesta){
                    listacerveza.add(cerveza);
                }else{
                    System.out.println("La birra ya existia");
                }
            }
        }
        //saca una cerveza segun el id
    public void quitarCerveza(int id){
        for (int i = 0; i < listacerveza.size(); i++) {
            if (listacerveza.get(i).getId() == id) {
                listacerveza.remove(i);
            } /*else {
                System.out.println("La cerveza q quiere robar no existe");
            }*/
        }
    }
//muestra
    public void listarCerveza(){
        for(int i =0;i<listacerveza.size();i++){
            System.out.println(listacerveza.get(i).getNombre());
        }
    }

    @Override
    public String toString() {
        return "Fabrica{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cerveza=" + listacerveza +
                '}';
    }
}
