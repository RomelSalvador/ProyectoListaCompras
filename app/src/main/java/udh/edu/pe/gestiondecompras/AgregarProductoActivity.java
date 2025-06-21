package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText etNombre, etCantidad, etAdquirido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
        etAdquirido = findViewById(R.id.etAdquirido);
    }

    public void guardarProducto(View view) {
        String nombre = etNombre.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();
        String adquirido = etAdquirido.getText().toString().trim();

        if (nombre.isEmpty() || cantidad.isEmpty() || adquirido.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Producto guardado correctamente", Toast.LENGTH_SHORT).show();

        }
    }
}
