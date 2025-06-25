package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private List<Lista> listaCompras;
    private Context context;

    public ListaAdapter(List<Lista> listaCompras, Context context) {
        this.listaCompras = listaCompras;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAdapter.ViewHolder holder, int position) {
        Lista item = listaCompras.get(position);
        holder.tvNombreLista.setText(item.getNombre());
        holder.tvFechaLista.setText("Fecha: " + item.getFecha());
    }

    @Override
    public int getItemCount() {
        return listaCompras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreLista, tvFechaLista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreLista = itemView.findViewById(R.id.tvNombreLista);
            tvFechaLista = itemView.findViewById(R.id.tvFechaLista);
        }
    }
}