package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroActivity extends AppCompatActivity {

    private EditText resNombres, resApellido, resCorreo, resContrasenia;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        resNombres = findViewById(R.id.resNombres);
        resApellido = findViewById(R.id.resApellido);
        resCorreo = findViewById(R.id.resCorreo);
        resContrasenia = findViewById(R.id.resContrasenia);

        db = FirebaseFirestore.getInstance();
    }

    public void Guardar(View view) {
        String nombre = resNombres.getText().toString().trim();
        String apellido = resApellido.getText().toString().trim();
        String correo = resCorreo.getText().toString().trim();
        String password = resContrasenia.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuario = new Usuario(nombre, apellido, correo, password);

        db.collection("usuarios")
                .add(usuario)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                    // Ir a LoginActivity
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                    intent.putExtra("correo_usuario", correo);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al registrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
