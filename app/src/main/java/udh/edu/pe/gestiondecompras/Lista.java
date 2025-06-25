package udh.edu.pe.gestiondecompras;

import java.util.List;

public class Lista {
    private String nombre;
    private String fecha;
    private List<Producto> productos;

    public Lista(String nombre, String fecha, List<Producto> productos) {
        this.nombre = nombre;
        this.fecha = fecha;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
