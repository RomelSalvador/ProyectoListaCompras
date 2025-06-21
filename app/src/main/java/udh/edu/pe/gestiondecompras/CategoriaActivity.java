package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriaActivity extends AppCompatActivity {

    private EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        etNombre = findViewById(R.id.etNombre);
    }

    public void guardarCategoria(View view) {
        String nombre = etNombre.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingrese el nombre de la categoría", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Categoría guardada: " + nombre, Toast.LENGTH_SHORT).show();
        }
    }
}
