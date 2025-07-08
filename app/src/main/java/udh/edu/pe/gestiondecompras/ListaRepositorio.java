package udh.edu.pe.gestiondecompras;

import java.util.ArrayList;
import java.util.List;

public class ListaRepositorio {
    private static final List<Lista> listas = new ArrayList<>();

    public static void agregarLista(Lista lista) {
        listas.add(lista);
    }

    public static List<Lista> obtenerTodas() {
        return new ArrayList<>(listas);
    }

    public static void actualizarLista(Lista listaActualizada) {
        for (int i = 0; i < listas.size(); i++) {
            if (listas.get(i).getNombre().equals(listaActualizada.getNombre())) {
                listas.set(i, listaActualizada);
                break;
            }
        }
    }
}

