package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoSeleccionAdapter extends RecyclerView.Adapter<ProductoSeleccionAdapter.ViewHolder> {

    private final List<Producto> listaProductos;
    private final List<Producto> productosSeleccionados;
    private final Context context;

    public ProductoSeleccionAdapter(Context context, List<Producto> listaProductos, List<Producto> productosSeleccionados) {
        this.context = context;
        this.listaProductos = listaProductos;
        this.productosSeleccionados = productosSeleccionados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_producto_seleccion, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.textViewNombre.setText(producto.getNombre());

        // Establecer estado inicial
        boolean seleccionado = productosSeleccionados.contains(producto);
        holder.checkBoxSeleccionado.setOnCheckedChangeListener(null);
        holder.checkBoxSeleccionado.setChecked(seleccionado);

        // Listener
        holder.checkBoxSeleccionado.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!productosSeleccionados.contains(producto)) {
                    productosSeleccionados.add(producto);
                }
            } else {
                productosSeleccionados.remove(producto);
            }
            producto.setAdquirido(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        CheckBox checkBoxSeleccionado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.tvNombreProducto);
            checkBoxSeleccionado = itemView.findViewById(R.id.checkBoxSeleccionar);
        }
    }
}
