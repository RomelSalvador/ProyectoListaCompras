package udh.edu.pe.gestiondecompras;

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

public class ListasCompartidasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaAdapter adapter;
    private List<Lista> listasCompartidas = new ArrayList<>();

    private FirebaseFirestore db;

    private String correoUsuario = "usuario1@gmail.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas_compartidas);

        recyclerView = findViewById(R.id.recyclerViewListasCompartidas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        cargarListasCompartidas();
    }

    private void cargarListasCompartidas() {
        db.collection("usuarios").document(correoUsuario)
                .collection("listas_compartidas")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    listasCompartidas.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Lista lista = doc.toObject(Lista.class);
                        listasCompartidas.add(lista);
                    }

                    adapter = new ListaAdapter(this, listasCompartidas, new ArrayList<>());
                    recyclerView.setAdapter(adapter);
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al cargar listas compartidas: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
