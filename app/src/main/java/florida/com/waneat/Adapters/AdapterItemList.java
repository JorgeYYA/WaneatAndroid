package florida.com.waneat.Adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by Usuario on 06/02/2018.
 */

public class AdapterItemList extends RecyclerView.Adapter<AdapterItemList.MyViewHolder> {

    private List<Product> productosLista =  new ArrayList<>();
    private List<Product> productosFiltrados = new ArrayList<>();
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private DecimalFormat dec = new DecimalFormat("#.00 €");;

    public AdapterItemList(List<Product> productosLista, @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.productosLista = productosLista;
        this.productosFiltrados = cloneList(productosLista);
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
        Product producto = productosLista.get(holder.getAdapterPosition());
        Ion.with(holder.image).load(producto.getImages().get(0).getImageUrl());
        holder.nombre.setText(producto.getNameProduct());
        holder.precio.setText(dec.format(producto.getPriceProduct()));
        holder.categoria.setText(producto.getCategoryProduct());
    }


    public void filter(String categoryFilter) {
        productosLista.clear();
        Log.d("FILTER", "entrada filter: "+categoryFilter);
        if(categoryFilter.equalsIgnoreCase("")){
            Log.d("FILTER", "entra a null");
            productosLista.addAll(this.productosFiltrados);
        }else{
            for(Product item: this.productosFiltrados){
                Log.d("FILTER", "categoryFilter:: "+categoryFilter);
                Log.d("FILTER", "getCategoryProduct:: "+item.getCategoryProduct());
                if(item.getCategoryProduct().equalsIgnoreCase(categoryFilter)){
                    productosLista.add(item);
                }
            }
            this.productosFiltrados.toString();
            Log.d("FILTER", "entra else::");

        }
        notifyDataSetChanged();
    }

    public static List<Product> cloneList (List<Product> list) {
        List<Product> clonedList = new ArrayList<Product>(list.size());
        for (Product dog : list) {
            clonedList.add(new Product(dog));
        }
        return clonedList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nombre, precio,categoria;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            //cv = (CardView) view.findViewById(R.id.card_view);
            image = (ImageView) view.findViewById(R.id.thumbnail);
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

