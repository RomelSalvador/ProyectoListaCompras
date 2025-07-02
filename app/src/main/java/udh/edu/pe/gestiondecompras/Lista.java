package udh.edu.pe.gestiondecompras;

import java.io.Serializable;
import java.util.List;

public class Lista implements Serializable {
    private String nombre;
    private String fecha;
    private String categoria;
    private List<Producto> productos;

    public Lista(String nombre, String fecha, String categoria, List<Producto> productos) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.categoria = categoria;
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
