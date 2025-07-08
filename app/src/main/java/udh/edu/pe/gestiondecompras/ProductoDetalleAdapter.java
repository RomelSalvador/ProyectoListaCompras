package udh.edu.pe.gestiondecompras;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoDetalleAdapter extends RecyclerView.Adapter<ProductoDetalleAdapter.ViewHolder> {

    private final List<Producto> productos;
    private final Runnable onProductoModificado;

    public ProductoDetalleAdapter(List<Producto> productos, Runnable onProductoModificado) {
        this.productos = productos;
        this.onProductoModificado = onProductoModificado;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_detalle, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);

        holder.tvNombre.setText(producto.getNombre());
        holder.tvCantidad.setText("Cantidad: " + producto.getCantidad());
        holder.checkBoxAdquirido.setChecked(producto.isAdquirido());

        actualizarEstiloTexto(holder, producto.isAdquirido());

        holder.checkBoxAdquirido.setOnCheckedChangeListener((buttonView, isChecked) -> {
            producto.setAdquirido(isChecked);
            actualizarEstiloTexto(holder, isChecked);
            onProductoModificado.run();
        });
    }

    private void actualizarEstiloTexto(ViewHolder holder, boolean adquirido) {
        if (adquirido) {
            holder.tvNombre.setPaintFlags(holder.tvNombre.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvNombre.setPaintFlags(holder.tvNombre.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidad;
        CheckBox checkBoxAdquirido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreProducto);
            tvCantidad = itemView.findViewById(R.id.tvCantidadProducto);
            checkBoxAdquirido = itemView.findViewById(R.id.checkBoxAdquirido);
        }
    }
}
