package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaComprasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaAdapter adapter;
    private List<Lista> todasLasListas;
    private Spinner spinnerFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);

        recyclerView = findViewById(R.id.recyclerViewListas);
        spinnerFiltro = findViewById(R.id.spinnerFiltroCategoria);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        todasLasListas = ListaRepositorio.obtenerTodas();


        String[] categorias = {"Todas", "Supermercado", "Farmacia", "Limpieza"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiltro.setAdapter(spinnerAdapter);


        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String categoriaSeleccionada = categorias[i];
                filtrarPorCategoria(categoriaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        adapter = new ListaAdapter(this, todasLasListas);
        recyclerView.setAdapter(adapter);
    }

    private void filtrarPorCategoria(String categoria) {
        if (categoria.equals("Todas")) {
            adapter = new ListaAdapter(this, todasLasListas);
        } else {
            List<Lista> filtradas = new ArrayList<>();
            for (Lista l : todasLasListas) {
                if (l.getCategoria().equalsIgnoreCase(categoria)) {
                    filtradas.add(l);
                }
            }
            adapter = new ListaAdapter(this, filtradas);
        }
        recyclerView.setAdapter(adapter);
    }
}
