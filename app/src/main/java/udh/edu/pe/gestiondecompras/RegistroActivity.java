package udh.edu.pe.gestiondecompras;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

import udh.edu.pe.gestiondecompras.LoginActivity;
import udh.edu.pe.gestiondecompras.R;

public class RegistroActivity extends AppCompatActivity {


    private EditText resNombres,resApellido, resCorreo, resContrasenia ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        resNombres = findViewById(R.id.resNombres);
        resApellido = findViewById(R.id.resApellido);
        resCorreo = findViewById(R.id.resCorreo);
        resContrasenia = findViewById(R.id.resContrasenia);
    }

    public void Guardar(View view) {
        String nombre = resNombres.getText().toString();
        String apellido = resApellido.getText().toString();
        String correo = resCorreo.getText().toString();
        String password = resContrasenia.getText().toString();


        Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);

        intent.putExtra("correo", correo);
        intent.putExtra("password", password);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        startActivity(intent);
    }

}
