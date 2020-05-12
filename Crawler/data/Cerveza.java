2
https://raw.githubusercontent.com/guimainini/Practica_Lab3_Parcial2019/master/src/Cerveza.java
public abstract class Cerveza {
    //atribitos
    private int id;
    private String nombre;
    private float precio;
    private float ABV;
    private String descripcion;

    //constructor
    public Cerveza(int id, String nombre, float precio, float ABV, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.ABV = ABV;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Cerveza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", ABV=" + ABV +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public abstract float calcularCostoFabricacion();



}
