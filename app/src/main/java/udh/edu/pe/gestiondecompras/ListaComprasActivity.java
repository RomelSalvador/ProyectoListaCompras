package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaComprasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaAdapter adapter;
    private Spinner spinnerFiltro;

    private FirebaseFirestore db;
    private List<Lista> todasLasListas = new ArrayList<>();
    private List<String> todasLasIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);

        recyclerView = findViewById(R.id.recyclerViewListas);
        spinnerFiltro = findViewById(R.id.spinnerFiltroCategoria);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

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
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        obtenerListasDesdeFirestore();
    }

    private void obtenerListasDesdeFirestore() {
        db.collection("listas_compras")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    todasLasListas.clear();
                    todasLasIds.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Lista lista = doc.toObject(Lista.class);
                        todasLasListas.add(lista);
                        todasLasIds.add(doc.getId());
                    }
                    filtrarPorCategoria("Todas");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar listas: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void filtrarPorCategoria(String categoria) {
        List<Lista> filtradas = new ArrayList<>();
        List<String> filtradasIds = new ArrayList<>();

        if (categoria.equals("Todas")) {
            filtradas.addAll(todasLasListas);
            filtradasIds.addAll(todasLasIds);
        } else {
            for (int i = 0; i < todasLasListas.size(); i++) {
                if (todasLasListas.get(i).getCategoria().equalsIgnoreCase(categoria)) {
                    filtradas.add(todasLasListas.get(i));
                    filtradasIds.add(todasLasIds.get(i));
                }
            }
        }

        adapter = new ListaAdapter(this, filtradas, filtradasIds);
        recyclerView.setAdapter(adapter);
    }
}
