package udh.edu.pe.gestiondecompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText etNombre, etCantidad;
    private int imagenSeleccionada = R.drawable.ic_launcher_foreground;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);

        db = FirebaseFirestore.getInstance();
    }

    public void guardarProducto(View view) {
        String nombre = etNombre.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();

        if (nombre.isEmpty() || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int cantidad = Integer.parseInt(cantidadStr);


        Producto nuevoProducto = new Producto(nombre, cantidad, imagenSeleccionada);
        ProductoRepositorio.agregarProducto(nuevoProducto);


        Map<String, Object> productoMap = new HashMap<>();
        productoMap.put("nombre", nombre);
        productoMap.put("cantidad", cantidad);
        productoMap.put("poster", imagenSeleccionada);
        productoMap.put("adquirido", false);

        db.collection("productos")
                .add(productoMap)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Producto guardado correctamente en Firestore", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(this, ListaProductosActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al guardar en Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
