package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("nombre") && intent.hasExtra("cantidad") && intent.hasExtra("poster")) {
            String nombre = intent.getStringExtra("nombre");
            int cantidad = intent.getIntExtra("cantidad", 0);
            int poster = intent.getIntExtra("poster", R.drawable.ic_launcher_foreground);

            Producto producto = new Producto(nombre, cantidad, poster);
            listaProductos.add(producto);
        }

        productoAdapter = new ProductoAdapter(listaProductos, this);
        recyclerViewProductos.setAdapter(productoAdapter);
    }
}
