2
https://raw.githubusercontent.com/guimainini/Practica_Lab3_Parcial2019/master/src/Receta.java
import java.util.ArrayList;

public class Receta {
    //atributo
    private int id;
    private String nombre;
    private String pais;
    private ArrayList<Ingrediente> listaingrediente;

    //constructor

    public Receta(int id, String nombre, String pais, ArrayList<Ingrediente> Listaingrediente) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.listaingrediente = Listaingrediente;
    }
    public Receta(){
    }
    //metodos
    //agrega un ingrediente al final de la lista
    public void agregarIngrediente(Ingrediente ingrediente){
        listaingrediente.add(ingrediente);
    }
    //remueve un ingrediente segun su id
    public void quitarIngrediente(int id){
        listaingrediente.get(id);
    }


    public ArrayList<Ingrediente> getListaingrediente() {
        return listaingrediente;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", Listaingrediente=" + listaingrediente +
                '}';
    }
}
