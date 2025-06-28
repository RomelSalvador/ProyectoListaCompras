package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AgregarListaComprasActivity extends AppCompatActivity {

    private EditText etNombreLista, etFechaLista;
    private RecyclerView recyclerView;
    private ProductoSeleccionAdapter adapter;
    private List<Producto> productosSeleccionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lista_compras);

        etNombreLista = findViewById(R.id.etNombreLista);
        etFechaLista = findViewById(R.id.etFechaLista);
        recyclerView = findViewById(R.id.recyclerViewSeleccionarProductos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductoSeleccionAdapter(this, ProductoRepositorio.obtenerProductos(), productosSeleccionados);
        recyclerView.setAdapter(adapter);
    }

    public void guardarLista(View view) {
        String nombre = etNombreLista.getText().toString().trim();
        String fecha = etFechaLista.getText().toString().trim();

        if (nombre.isEmpty() || fecha.isEmpty() || productosSeleccionados.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos y seleccione al menos un producto", Toast.LENGTH_SHORT).show();
        } else {
            Lista nuevaLista = new Lista(nombre, fecha, new ArrayList<>(productosSeleccionados));
            ListaRepositorio.agregarLista(nuevaLista);

            Toast.makeText(this, "Lista guardada con éxito", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListaComprasActivity.class);
            startActivity(intent);
            finish(); // Finaliza esta Activity
        }
    }
}
