package florida.com.waneat.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by 2dam on 09/02/2018.
 */

public class AdapterFinalizarCompra  extends RecyclerView.Adapter<AdapterFinalizarCompra.MyViewHolder> {

    private List<Product> productosLista;

    public AdapterFinalizarCompra(List<Product> productosLista) {
        this.productosLista = productosLista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cesta_list, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product producto = productosLista.get(holder.getAdapterPosition());
        //le añadimos el id en el que se clicka al boton de añadir y de borrar respectivamente


        holder.cantidad.setText(Integer.toString(producto.getCantidad()));
        holder.nombre_producto.setText(producto.getNameProduct());
        holder.precio.setText(String.valueOf(producto.getPriceProduct())+"€");
    }

    @Override
    public int getItemCount() {
        return productosLista.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView cantidad, nombre_producto, precio;

        public MyViewHolder(View view) {
            super(view);
            cantidad = (TextView) view.findViewById(R.id.cantidad);
            nombre_producto = (TextView) view.findViewById(R.id.nombre_producto);
            precio = (TextView) view.findViewById(R.id.precio);

        }
    }


}