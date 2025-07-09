package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnAgregarLista, btnAgregarProducto, btnListaProductos, btnListaCompras, btnCompartirLista,btnVerListasCompartidas, btnCerrarSesion;
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
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCompartirLista = findViewById(R.id.btnCompartirLista);
        btnVerListasCompartidas = findViewById(R.id.btnVerListasCompartidas);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        if (usuario != null) {
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
            tvBienvenido.setText("Bienvenido, " + nombreCompleto);
        } else {
            tvBienvenido.setText("Gestión de Listas de Compras");
        }

        btnAgregarLista.setOnClickListener(v -> startActivity(new Intent(this, AgregarListaComprasActivity.class)));
        btnAgregarProducto.setOnClickListener(v -> startActivity(new Intent(this, AgregarProductoActivity.class)));
        btnListaProductos.setOnClickListener(v -> startActivity(new Intent(this, ListaProductosActivity.class)));
        btnListaCompras.setOnClickListener(v -> startActivity(new Intent(this, ListaComprasActivity.class)));
        btnCompartirLista.setOnClickListener(v -> startActivity(new Intent(this, SeleccionarListaActivity.class)));
        btnVerListasCompartidas.setOnClickListener(v -> startActivity(new Intent(this, ListasCompartidasActivity.class)));

        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());
    }

    private void cerrarSesion() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
