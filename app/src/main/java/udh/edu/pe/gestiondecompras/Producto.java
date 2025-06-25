package udh.edu.pe.gestiondecompras;

public class Producto {
    private String nombre;
    private int cantidad;
    private int poster;

    public Producto(String nombre, int cantidad, int poster) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.poster = poster;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}