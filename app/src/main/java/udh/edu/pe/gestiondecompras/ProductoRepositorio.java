package udh.edu.pe.gestiondecompras;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio {
    private static final List<Producto> listaProductos = new ArrayList<>();

    public static void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public static List<Producto> obtenerProductos() {
        return listaProductos;
    }
}

