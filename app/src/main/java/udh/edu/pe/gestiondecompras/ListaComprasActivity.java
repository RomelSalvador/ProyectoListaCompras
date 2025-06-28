package udh.edu.pe.gestiondecompras;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaComprasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);

        recyclerViewListas = findViewById(R.id.recyclerViewListas);
        recyclerViewListas.setLayoutManager(new LinearLayoutManager(this));

        ListaAdapter adapter = new ListaAdapter(this, ListaRepositorio.obtenerTodas());
        recyclerViewListas.setAdapter(adapter);
    }
}
