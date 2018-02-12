package florida.com.waneat.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by Usuario on 06/02/2018.
 */

public class AdapterItemList extends RecyclerView.Adapter<AdapterItemList.MyViewHolder> {

    private List<Product> productosLista;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private DecimalFormat dec = new DecimalFormat("#.00 €");;

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
        holder.precio.setText(dec.format(producto.getPrecio()));
        holder.categoria.setText(producto.getCategoria());


        // holder.thumnail.setImageDrawable(producto.get(position).getImagenDrawable());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nombre, precio,categoria;
       // public CardView cv;

        public MyViewHolder(View view) {
            super(view);
            //cv = (CardView) view.findViewById(R.id.card_view);
            nombre = (TextView) view.findViewById(R.id.dishName);
            precio = (TextView) view.findViewById(R.id.dishPrice);
            categoria = (TextView) view.findViewById(R.id.categoria);
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

