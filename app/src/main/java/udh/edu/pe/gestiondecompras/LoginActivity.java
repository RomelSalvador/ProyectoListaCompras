package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo, etContrasenia;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.etCorreo);
        etContrasenia = findViewById(R.id.etContrasenia);
        db = FirebaseFirestore.getInstance();

        String correo = getIntent().getStringExtra("correo_usuario");
        if (correo != null) {
            etCorreo.setText(correo);
        }
    }

    public void irARegistro(View view) {
        startActivity(new Intent(this, RegistroActivity.class));
    }

    public void iniciarSesion(View view) {

        String correoIngresado = etCorreo.getText().toString().trim();
        String contraseniaIngresado = etContrasenia.getText().toString().trim();

        if (correoIngresado.isEmpty() || contraseniaIngresado.isEmpty()) {
            Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if (correoIngresado.equals("adminstiven") && contraseniaIngresado.equals("123456789")) {
            Usuario admin = new Usuario("Administrador", "Sin Apellido", "adminCorreo", "123456789");
            Toast.makeText(this, "Inicio de sesión como administrador", Toast.LENGTH_SHORT).show();
            abrirPrincipal(admin);
            return;
        }

        db.collection("usuarios")
                .whereEqualTo("correo", correoIngresado)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        boolean loginExitoso = false;

                        for (QueryDocumentSnapshot doc : querySnapshot) {
                            String contrasenaDB = doc.getString("contrasena");
                            String nombreDB = doc.getString("nombre");
                            String apellidoDB = doc.getString("apellido");
                            String correoDB = doc.getString("correo");

                            if (contrasenaDB != null && contrasenaDB.equals(contraseniaIngresado)) {
                                loginExitoso = true;
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                Usuario usuario = new Usuario(nombreDB, apellidoDB, correoDB, contrasenaDB);
                                abrirPrincipal(usuario);
                                break;
                            }
                        }

                        if (!loginExitoso) {
                            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al consultar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void abrirPrincipal(Usuario usuario) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}
