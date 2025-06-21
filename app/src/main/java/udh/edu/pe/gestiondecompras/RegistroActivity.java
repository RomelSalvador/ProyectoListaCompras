package udh.edu.pe.gestiondecompras;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import udh.edu.pe.gestiondecompras.LoginActivity;
import udh.edu.pe.gestiondecompras.R;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombres, etCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombres = findViewById(R.id.etNombres);
        etCorreo = findViewById(R.id.etCorreo);
    }

    public void Guardar(View view) {
        String nombre = etNombres.getText().toString();
        String correo = etCorreo.getText().toString();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("nombre_usuario", nombre);
        intent.putExtra("correo_usuario", correo);
        startActivity(intent);
    }
}
