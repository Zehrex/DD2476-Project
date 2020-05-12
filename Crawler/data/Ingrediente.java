2
https://raw.githubusercontent.com/guimainini/Practica_Lab3_Parcial2019/master/src/Ingrediente.java
import java.util.ArrayList;

public class Ingrediente {
    //atributos
    private int id;
    private String nombre;
    private String description;
    private float cantidad;
    private float precioUnidad;


    //constructor
    public Ingrediente(int id, String nombre, String description, float cantidad, float precioUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.description = description;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
    }

    //metodos
    //busco la cantidad que tiene cada ingrediente y lo imprimo
    public void indicarCantidad(ArrayList<Ingrediente> listaIngredientes,String nombre){

        for(int i = 0;i<listaIngredientes.size();i++){
            if(listaIngredientes.get(i).getNombre().equals(nombre)){
                System.out.println("La cantidad de estes producto es : "+listaIngredientes.get(i).getCantidad());
            }
        }
    }

    public String getNombre() {
        return nombre;
    }
    public float getCantidad(){
        return cantidad;
    }
    public float getPrecioUnidad() {
        return precioUnidad;
    }

    @Override
    public String toString() {
        return "Intrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", description='" + description + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnidad=" + precioUnidad +
                '}';
    }
}
