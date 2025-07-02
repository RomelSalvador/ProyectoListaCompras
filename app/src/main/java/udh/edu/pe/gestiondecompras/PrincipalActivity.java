package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnAgregarLista, btnAgregarProducto, btnListaProductos, btnListaCompras, btnCerrarSesion;
    private TextView tvBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tvBienvenido = findViewById(R.id.tvBienvenida);
        btnAgregarLista = findViewById(R.id.btnAgregarLista);
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        btnListaProductos = findViewById(R.id.btnListaProductos);
        btnListaCompras = findViewById(R.id.btnListaCompras);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion); //

        tvBienvenido.setText("Gestión de Listas de Compras");

        btnAgregarLista.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarListaComprasActivity.class);
            startActivity(intent);
        });

        btnAgregarProducto.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarProductoActivity.class);
            startActivity(intent);
        });

        btnListaProductos.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        });

        btnListaCompras.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaComprasActivity.class);
            startActivity(intent);
        });


        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());
    }

    private void cerrarSesion() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
