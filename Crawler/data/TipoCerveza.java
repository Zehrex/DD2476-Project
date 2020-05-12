2
https://raw.githubusercontent.com/guimainini/Practica_Lab3_Parcial2019/master/src/TipoCerveza.java
public abstract class TipoCerveza extends Cerveza{
    //atributo
    private boolean gluten = true;
    private Receta receta;
    private float porcentajeComplejidad;

    //constructores

    public TipoCerveza(int id, String nombre, float precio, float ABV, String descripcion, boolean gluten, Receta receta, float porcentajeComplejidad) {
        super(id, nombre, precio, ABV, descripcion);
        this.gluten = gluten;
        this.receta = receta;
        this.porcentajeComplejidad = porcentajeComplejidad;
    }

    //metodos


    public Receta getReceta() {
        return receta;
    }

    public float getPorcentajeComplejidad() {
        return porcentajeComplejidad;
    }

    public abstract float calcularCostoFabricacion();


    //es un metodo abstracto , hice un static asi puedo hacer con gluten aca , tambien hice un sobrecarga de metodos
    //no creo q este bien
    public static float calcularCostoFabricacion(Receta receta,boolean gluten) {
        int contador = 0;

        if (gluten == false) {
            for (int i = 0; i < receta.getListaingrediente().size(); i++) {
                contador += receta.getListaingrediente().get(i).getPrecioUnidad();
            }
            contador += contador * 0.02;
        }else{
            System.out.println("No se puede hacer con Gluten");
        }
            return contador;
    }

    @Override
    public String toString() {
        return "TipoCerveza{" +
                "gluten=" + gluten +
                ", receta=" + receta +
                ", porcentajeComplejidad=" + porcentajeComplejidad +
                '}';
    }
}
