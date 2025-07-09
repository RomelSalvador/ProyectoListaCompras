package udh.edu.pe.gestiondecompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioSeleccionAdapter extends RecyclerView.Adapter<UsuarioSeleccionAdapter.ViewHolder> {

    private final List<Usuario> usuarios;
    private final Context context;
    private final OnUsuarioSeleccionadoListener listener;

    public interface OnUsuarioSeleccionadoListener {
        void onUsuarioSeleccionado(Usuario usuario);
    }

    public UsuarioSeleccionAdapter(Context context, List<Usuario> usuarios, OnUsuarioSeleccionadoListener listener) {
        this.context = context;
        this.usuarios = usuarios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_usuario_seleccion, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.textViewCorreo.setText(usuario.getCorreo());

        holder.itemView.setOnClickListener(v -> {
            listener.onUsuarioSeleccionado(usuario);
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCorreo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCorreo = itemView.findViewById(R.id.tvCorreoUsuario);
        }
    }
}
