package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetalleListaActivity extends AppCompatActivity {

    private TextView tvNombreLista;
    private RecyclerView recyclerView;
    private ProductoDetalleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista);

        tvNombreLista = findViewById(R.id.tvNombreListaDetalle);
        recyclerView = findViewById(R.id.recyclerViewDetalleLista);

        Lista listaSeleccionada = (Lista) getIntent().getSerializableExtra("lista");

        if (listaSeleccionada != null) {
            tvNombreLista.setText("Lista: " + listaSeleccionada.getNombre());

            List<Producto> productos = listaSeleccionada.getProductos();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ProductoDetalleAdapter(productos);
            recyclerView.setAdapter(adapter);
        }
    }
}
