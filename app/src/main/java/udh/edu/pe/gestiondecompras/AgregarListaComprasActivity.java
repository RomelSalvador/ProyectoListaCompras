package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AgregarListaComprasActivity extends AppCompatActivity {

    private EditText etNombreLista, etFechaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lista_compras);

        etNombreLista = findViewById(R.id.etNombreLista);
        etFechaLista = findViewById(R.id.etFechaLista);
    }

    public void guardarLista(View view) {
        String nombre = etNombreLista.getText().toString().trim();
        String fecha = etFechaLista.getText().toString().trim();

        if (nombre.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            Lista nuevaLista = new Lista(nombre, fecha, new ArrayList<>());

            Intent intent = new Intent(this, AgregarListaComprasActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("fecha", fecha);
            startActivity(intent);
        }
    }
}
