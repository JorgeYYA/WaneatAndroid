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
 * Created by Usuario on 06/02/2018.
 */

public class AdapterItemList extends RecyclerView.Adapter<AdapterItemList.MyViewHolder> {

    private List<Product> productosLista;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;


    public AdapterItemList(List<Product> productosLista, @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.productosLista = productosLista;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    @Override
    public int getItemCount() {
        return productosLista.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product producto = productosLista.get(position);
        holder.nombre.setText(producto.getNombre());
        holder.precio.setText(String.valueOf(producto.getPrecio()));


        // holder.thumnail.setImageDrawable(producto.get(position).getImagenDrawable());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nombre, precio;
       // public CardView cv;

        public MyViewHolder(View view) {
            super(view);
            //cv = (CardView) view.findViewById(R.id.card_view);
            nombre = (TextView) view.findViewById(R.id.dishName);
            precio = (TextView) view.findViewById(R.id.dishPrice);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick (View v){
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public interface RecyclerViewOnItemClickListener {

        void onClick(View v, int position);
    }

}

