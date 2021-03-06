package florida.com.waneat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by sergiomoreno on 5/2/18.
 */

public class AdapterCartItem  extends RecyclerView.Adapter<AdapterCartItem.MyViewHolder> {

    private List<Product> productosLista;
    private BtnClickListener BtnClickListener;
    private Context context;

    public AdapterCartItem(Context context, List<Product> productosLista, @NonNull BtnClickListener BtnClickListener) {
        this.productosLista = productosLista;
        this.context = context;
        this.BtnClickListener = BtnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product producto = productosLista.get(position);
        //le añadimos el id en el que se clicka al boton de añadir y de borrar respectivamente
        holder.add.setTag(holder.getAdapterPosition());
        holder.remove.setTag(holder.getAdapterPosition());


        holder.nombre.setText(producto.getNameProduct());
        holder.cantidad.setText(Integer.toString(producto.getCantidad()));
        holder.precio.setText(String.valueOf(producto.getPriceProduct())+ context.getResources().getString(R.string.badge));
        Ion.with(holder.fotoProducto).load(producto.getImages().get(0).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return productosLista.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nombre, precio, cantidad;
        public ImageView fotoProducto;
        public Button add, remove;

        public MyViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombreProducto);
            precio = (TextView) view.findViewById(R.id.precioProducto);
            cantidad = (TextView) view.findViewById(R.id.cantidadProducto);
            fotoProducto = (ImageView) view.findViewById(R.id.miniatura);
            add = (Button) view.findViewById(R.id.addProduct);
            remove = (Button) view.findViewById(R.id.removeProduct);
            addProducts();
            removeProducts();
        }

        private void addProducts(){
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BtnClickListener.onAddProducts((Integer)view.getTag());
                }
            });
        }
        private void removeProducts(){
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BtnClickListener.onRemoveProducts((Integer)view.getTag());
                }
            });
        }
    }




    public interface BtnClickListener {
        void onAddProducts(int position);
        void onRemoveProducts(int position);
    }
}