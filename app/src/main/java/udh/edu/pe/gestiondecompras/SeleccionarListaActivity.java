package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private List<Lista> listas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_lista);

        recyclerView = findViewById(R.id.recyclerViewListas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        obtenerListas();
    }

    private void obtenerListas() {
        db.collection("listas")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    listas.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Lista lista = doc.toObject(Lista.class);
                        listas.add(lista);
                    }
                    configurarAdapter();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al cargar listas: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void configurarAdapter() {
        ListaSeleccionAdapter adapter = new ListaSeleccionAdapter(this, listas, listaSeleccionada -> {
            Intent intent = new Intent(this, CompartirListaActivity.class);
            intent.putExtra("lista", listaSeleccionada);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
