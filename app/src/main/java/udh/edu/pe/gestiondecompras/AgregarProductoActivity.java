package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText etNombre, etCantidad;
    private Spinner spinnerCategoria;
    private int imagenSeleccionada = R.drawable.ic_launcher_foreground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);


        String[] categorias = {"supermercado", "Limpieza", "farmacia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);
    }

    public void guardarProducto(View view) {
        String nombre = etNombre.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();
        String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();

        if (nombre.isEmpty() || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            int cantidad = Integer.parseInt(cantidadStr);


            Intent intent = new Intent(this, ListaProductosActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("cantidad", cantidad);
            intent.putExtra("poster", imagenSeleccionada);
            intent.putExtra("categoria", categoriaSeleccionada);
            startActivity(intent);
        }
    }
}
