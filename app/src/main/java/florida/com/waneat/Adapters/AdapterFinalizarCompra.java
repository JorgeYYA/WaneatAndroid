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

public class AdapterFinalizarCompra
        extends RecyclerView.Adapter<AdapterFinalizarCompra.ProductViewHolder>
        implements View.OnClickListener {
    private List<Product> items;
    private View.OnClickListener listener;


    public AdapterFinalizarCompra(List<Product> items) {
        this.items = items;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cesta_list, viewGroup, false);
        v.setOnClickListener(this);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
       Product item = items.get(i);
        viewHolder.bindProducto(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView cantidad, nombre_producto, precio;


        public ProductViewHolder(View v) {
            super(v);
            cantidad = (TextView) v.findViewById(R.id.cantidad);
            nombre_producto = (TextView) v.findViewById(R.id.nombre_producto);
            precio = (TextView) v.findViewById(R.id.precio);

        }

        public void bindProducto(Product item) {
            cantidad.setText(item.getCantidad());
            nombre_producto.setText(item.getNombre());
            precio.setText(String.valueOf(item.getPrecio()));

        }
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}