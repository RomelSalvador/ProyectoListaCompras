package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private final List<Lista> listas;
    private final List<String> listaIds;
    private final Context context;

    public ListaAdapter(Context context, List<Lista> listas, List<String> listaIds) {
        this.context = context;
        this.listas = listas;
        this.listaIds = listaIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.lista_items, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lista lista = listas.get(position);
        holder.tvNombreLista.setText("Nombre: " + lista.getNombre());
        holder.tvFechaLista.setText("Fecha: " + lista.getFecha());
        holder.tvCategoriaLista.setText("Categoría: " + lista.getCategoria());

        StringBuilder productosTexto = new StringBuilder("Productos:\n");
        for (Producto p : lista.getProductos()) {
            productosTexto.append("- ").append(p.getNombre()).append(" x").append(p.getCantidad()).append("\n");
        }
        holder.tvProductos.setText(productosTexto.toString().trim());

        String listaId = listaIds.get(position);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleListaActivity.class);
            intent.putExtra("lista", lista);
            intent.putExtra("listaId", listaId);
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, CompartirListaActivity.class);
            intent.putExtra("lista", lista);
            intent.putExtra("listaId", listaId);
            context.startActivity(intent);
            return true;
        });
    }


    @Override
    public int getItemCount() {
        return listas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreLista, tvFechaLista, tvProductos, tvCategoriaLista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreLista = itemView.findViewById(R.id.tvNombreLista);
            tvFechaLista = itemView.findViewById(R.id.tvFechaLista);
            tvCategoriaLista = itemView.findViewById(R.id.tvCategoriaLista);
            tvProductos = itemView.findViewById(R.id.tvProductos);
        }
    }
}
