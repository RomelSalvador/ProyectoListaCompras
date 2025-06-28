package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText etNombre, etCantidad;
    private int imagenSeleccionada = R.drawable.ic_launcher_foreground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
    }

    public void guardarProducto(View view) {
        String nombre = etNombre.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();

        if (nombre.isEmpty() || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            int cantidad = Integer.parseInt(cantidadStr);

            Producto nuevoProducto = new Producto(nombre, cantidad, imagenSeleccionada);
            ProductoRepositorio.agregarProducto(nuevoProducto);

            Intent intent = new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        }
    }
}
