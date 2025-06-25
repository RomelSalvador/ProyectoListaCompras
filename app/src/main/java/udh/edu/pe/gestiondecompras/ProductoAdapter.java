package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    List<Producto> listaProductos;
    Context context;

    public ProductoAdapter(List<Producto> listaProductos, Context context) {
        this.listaProductos = listaProductos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.producto_items, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewCantidad.setText("Cantidad: " + producto.getCantidad());
        holder.textViewCategoria.setText("Categoría: " + producto.getCategoria());

        Drawable imagen = context.getResources().getDrawable(producto.getPoster());
        holder.imageViewPoster.setImageDrawable(imagen);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewPoster;
        TextView textViewNombre;
        TextView textViewCantidad;
        TextView textViewCategoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.ivPoster);
            textViewNombre = itemView.findViewById(R.id.tvNombreProducto);
            textViewCantidad = itemView.findViewById(R.id.tvCantidad);
            textViewCategoria = itemView.findViewById(R.id.tvCategoria);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Producto producto = listaProductos.get(getAdapterPosition());
            Toast.makeText(context, "Producto: " + producto.getNombre(), Toast.LENGTH_SHORT).show();
        }
    }
}
