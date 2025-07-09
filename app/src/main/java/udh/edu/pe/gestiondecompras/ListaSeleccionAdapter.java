package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaSeleccionAdapter extends RecyclerView.Adapter<ListaSeleccionAdapter.ViewHolder> {

    private final List<Lista> listas;
    private final Context context;
    private final OnListaClickListener listener;

    public interface OnListaClickListener {
        void onListaClick(Lista listaSeleccionada);
    }

    public ListaSeleccionAdapter(Context context, List<Lista> listas, OnListaClickListener listener) {
        this.context = context;
        this.listas = listas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lista lista = listas.get(position);
        holder.tvNombreLista.setText("Nombre: " + lista.getNombre());
        holder.tvFechaLista.setText("Fecha: " + lista.getFecha());
        holder.tvCategoriaLista.setText("Categoría: " + lista.getCategoria());

        holder.itemView.setOnClickListener(v -> listener.onListaClick(lista));
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreLista, tvFechaLista, tvCategoriaLista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreLista = itemView.findViewById(R.id.tvNombreLista);
            tvFechaLista = itemView.findViewById(R.id.tvFechaLista);
            tvCategoriaLista = itemView.findViewById(R.id.tvCategoriaLista);
        }
    }
}
