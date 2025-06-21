package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombres, etApellido, etCorreo, etContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Referenciando los campos del layout
        etNombres = findViewById(R.id.etNombres);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
    }

    // Método que se ejecuta al presionar el botón "Guardar"
    public void Guardar(View view) {
        String nombres = etNombres.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();

        if (nombres.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Registro exitoso:\n" + nombres + " " + apellido, Toast.LENGTH_LONG).show();
        }
    }
}
