package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NuevaListaActivity extends AppCompatActivity {

    private EditText etNombre, etFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_lista);

        etNombre = findViewById(R.id.etNombre);
        etFecha = findViewById(R.id.Fecha);
    }

    public void guardarLista(View view) {
        String nombreLista = etNombre.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();

        if (nombreLista.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Lista guardada:\n" + nombreLista + "\nFecha: " + fecha, Toast.LENGTH_LONG).show();
        }
    }
}
