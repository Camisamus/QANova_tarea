package TestClases.Model;

public class Producto {
    String nombre;
    int precio;
    int calificacion;
    String[] descipcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String[] getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String[] descipcion) {
        this.descipcion = descipcion;
    }

}
