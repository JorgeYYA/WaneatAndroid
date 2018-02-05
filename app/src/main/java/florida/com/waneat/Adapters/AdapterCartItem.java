package florida.com.waneat.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by sergiomoreno on 5/2/18.
 */

public class AdapterCartItem  extends RecyclerView.Adapter<AdapterCartItem.MyViewHolder> {

    private List<Product> productosLista;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public AdapterCartItem(List<Product> productosLista, @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.productosLista = productosLista;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product producto = productosLista.get(position);
        holder.nombre.setText(producto.getNombre());
        holder.comentariosAdicionales.setText(producto.getComentariosAdicionales());
        holder.cantidad.setText(producto.getCantidad());
        holder.precio.setText(producto.getCantidad());
    }

    @Override
    public int getItemCount() {
        return productosLista.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView nombre, comentariosAdicionales, precio, cantidad;

        public MyViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombreProducto);
            comentariosAdicionales = (TextView) view.findViewById(R.id.comentariosAdicionalesProducto);
            precio = (TextView) view.findViewById(R.id.precioProducto);
            cantidad = (TextView) view.findViewById(R.id.cantidadProducto);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }

    }


    public interface  RecyclerViewOnItemClickListener {

        void onClick(View v, int position);
    }
}