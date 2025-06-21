package udh.edu.pe.gestiondecompras;

public class Producto {
    private String nombre;
    private int cantidad;
    private boolean adquirido;

    public Producto(String nombre, int cantidad, boolean adquirido) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.adquirido = adquirido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isAdquirido() {
        return adquirido;
    }
}