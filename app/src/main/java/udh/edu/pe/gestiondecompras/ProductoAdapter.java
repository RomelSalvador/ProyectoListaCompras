package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

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

        Drawable imagen = context.getResources().getDrawable(producto.getPoster());
        holder.imageViewPoster.setImageDrawable(imagen);

        holder.checkBoxAdquirido.setOnCheckedChangeListener(null);
        holder.checkBoxAdquirido.setChecked(producto.isAdquirido());

        holder.checkBoxAdquirido.setOnCheckedChangeListener((buttonView, isChecked) -> {
            producto.setAdquirido(isChecked);
            Toast.makeText(context, "Estado actualizado: " + producto.getNombre() +
                    (isChecked ? " (Adquirido)" : " (Pendiente)"), Toast.LENGTH_SHORT).show();

            // 🔥 ACTUALIZAR EN FIRESTORE
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("productos")
                    .whereEqualTo("nombre", producto.getNombre()) // ⚠️ Mejor usa ID en lugar de nombre
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        if (!querySnapshot.isEmpty()) {
                            querySnapshot.getDocuments().get(0).getReference()
                                    .update("adquirido", isChecked);
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPoster;
        TextView textViewNombre;
        TextView textViewCantidad;
        CheckBox checkBoxAdquirido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.ivPoster);
            textViewNombre = itemView.findViewById(R.id.tvNombreProducto);
            textViewCantidad = itemView.findViewById(R.id.tvCantidad);
            checkBoxAdquirido = itemView.findViewById(R.id.checkBoxAdquirido);
        }
    }
}
