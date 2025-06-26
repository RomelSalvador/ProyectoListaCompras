package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo, etContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.etCorreo);
        etContrasenia = findViewById(R.id.etContrasenia);


        String correo = getIntent().getStringExtra("correo_usuario");
        if (correo != null) {
            etCorreo.setText(correo);
        }
    }

    public void irARegistro(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view) {

        String correoIngresado = etCorreo.getText().toString().trim();
        String contraseniaIngresado = etContrasenia.getText().toString().trim();

        String adminCorreo = "adminstiven";
        String contrasenia = "123456789";

        if (correoIngresado.equals(adminCorreo) && contraseniaIngresado.equals(contrasenia)) {
            Toast.makeText(this, "Inicio de sesión como administrador", Toast.LENGTH_SHORT).show();

            Usuario admin = new Usuario("Administrador", "adminCorreo","contrasenia");

            Intent intentLogin = new Intent(LoginActivity.this, PrincipalActivity.class);
            intentLogin.putExtra("usuario", admin);
            startActivity(intentLogin);
            return;
        }

        Intent intent = getIntent();
        String correoRegistrado = intent.getStringExtra("correo");
        String passwordRegistrada = intent.getStringExtra("password");
        String nombreRegistrado = intent.getStringExtra("nombre");


        if (correoIngresado.equals(correoRegistrado) && contraseniaIngresado.equals(passwordRegistrada)) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

            Usuario usuario = new Usuario("nombreRegistrado", "correoRegistrado",  "passwordRegistrada");
            

            Intent intentLogin = new Intent(LoginActivity.this, PrincipalActivity.class);
            intentLogin.putExtra("usuario", usuario);
            startActivity(intentLogin);
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

}
