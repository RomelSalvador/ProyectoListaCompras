package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DetalleListaActivity extends AppCompatActivity {

    private TextView tvNombreLista;
    private RecyclerView recyclerView;
    private ProductoDetalleAdapter adapter;
    private Lista listaSeleccionada;
    private FirebaseFirestore db;
    private String listaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista);

        tvNombreLista = findViewById(R.id.tvNombreListaDetalle);
        recyclerView = findViewById(R.id.recyclerViewDetalleLista);
        db = FirebaseFirestore.getInstance();

        listaSeleccionada = (Lista) getIntent().getSerializableExtra("lista");
        listaId = getIntent().getStringExtra("listaId");

        if (listaSeleccionada != null) {
            tvNombreLista.setText("Lista: " + listaSeleccionada.getNombre());

            List<Producto> productos = listaSeleccionada.getProductos();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new ProductoDetalleAdapter(productos, this::guardarCambiosLista);
            recyclerView.setAdapter(adapter);
        }
    }

    private void guardarCambiosLista() {
        if (listaId != null) {
            DocumentReference listaRef = db.collection("listas").document(listaId);

            listaRef.update("productos", listaSeleccionada.getProductos())
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Lista actualizada", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "Error: ID de la lista no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
