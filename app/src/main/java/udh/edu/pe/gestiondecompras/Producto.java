package udh.edu.pe.gestiondecompras;

import java.io.Serializable;

public class Producto implements Serializable  {
    private String nombre;
    private int cantidad;
    private int poster;
    private boolean adquirido;

    public Producto(String nombre, int cantidad, int poster) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.poster = poster;
        this.adquirido = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isAdquirido() {
        return adquirido;
    }

    public void setAdquirido(boolean adquirido) {
        this.adquirido = adquirido;
    }
}