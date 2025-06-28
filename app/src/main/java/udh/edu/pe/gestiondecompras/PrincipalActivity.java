package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnAgregarLista, btnAgregarProducto, btnListaProductos, btnListaCompras;
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
    }
}