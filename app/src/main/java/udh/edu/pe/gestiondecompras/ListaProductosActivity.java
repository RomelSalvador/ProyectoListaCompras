package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        cargarProductosDesdeFirestore();
    }

    private void cargarProductosDesdeFirestore() {
        db.collection("productos")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<Producto> listaProductos = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        String nombre = doc.getString("nombre");
                        Long cantidadLong = doc.getLong("cantidad");
                        Long posterLong = doc.getLong("poster");
                        Boolean adquirido = doc.getBoolean("adquirido");

                        int cantidad = cantidadLong != null ? cantidadLong.intValue() : 0;
                        int poster = posterLong != null ? posterLong.intValue() : R.drawable.ic_launcher_foreground;

                        Producto producto = new Producto(nombre, cantidad, poster);
                        if (adquirido != null) {
                            producto.setAdquirido(adquirido);
                        }
                        listaProductos.add(producto);
                    }

                    productoAdapter = new ProductoAdapter(listaProductos, this);
                    recyclerViewProductos.setAdapter(productoAdapter);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar productos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
