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

public class CompartirListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuarioSeleccionAdapter adapter;
    private List<Usuario> usuarios = new ArrayList<>();
    private Lista listaCompartida;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir_lista);

        recyclerView = findViewById(R.id.recyclerViewUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaCompartida = (Lista) getIntent().getSerializableExtra("lista");

        db = FirebaseFirestore.getInstance();

        obtenerUsuarios();
    }

    private void obtenerUsuarios() {
        String correoActual = getSharedPreferences("MiAppPrefs", MODE_PRIVATE).getString("correoUsuario", "");
        db.collection("usuarios")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    usuarios.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Usuario usuario = doc.toObject(Usuario.class);
                        if (!usuario.getCorreo().equals(correoActual)) {
                            usuarios.add(usuario);
                        }
                    }
                    configurarAdapter();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al cargar usuarios: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void configurarAdapter() {
        adapter = new UsuarioSeleccionAdapter(this, usuarios, this::compartirLista);
        recyclerView.setAdapter(adapter);
    }

    private void compartirLista(Usuario usuario) {
        Toast.makeText(this, "Compartiendo '" + listaCompartida.getNombre() + "' con " + usuario.getCorreo(), Toast.LENGTH_SHORT).show();

        db.collection("usuarios").document(usuario.getCorreo())
                .collection("listas_compartidas").document(listaCompartida.getNombre())
                .set(listaCompartida)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Lista compartida correctamente con " + usuario.getCorreo(), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Error al compartir: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
