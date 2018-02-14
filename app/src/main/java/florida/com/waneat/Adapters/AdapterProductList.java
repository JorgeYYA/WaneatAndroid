package florida.com.waneat.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Fragments.OrderList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by JorgeYYA on 08/02/2018.
 */


public class AdapterProductList extends RecyclerView.Adapter<AdapterProductList.ClaseViewHolder>{

    public static class ClaseViewHolder extends RecyclerView.ViewHolder {

        TextView prodName, quantity, prize;

        ClaseViewHolder(View itemView) {
            super(itemView);

            prodName = itemView.findViewById(R.id.prod_name);
            quantity = itemView.findViewById(R.id.quantity);
            prize = itemView.findViewById(R.id.prize);


        }

        public void bind(final Product item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }

    }



    private final OnItemClickListener listener;
    ArrayList<Product> products;

    public AdapterProductList(ArrayList<Product> products, OnItemClickListener listener){
        this.products = products;
        this.listener = listener;
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public ClaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, viewGroup, false);
        ClaseViewHolder pvh = new ClaseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ClaseViewHolder ViewHolder, final int i) {


        ViewHolder.bind(products.get(i), listener);

        int numProd;

        String list = "";



        ViewHolder.prodName.setText(products.get(i).getName_product());
        ViewHolder.quantity.setText(products.get(i).getCantidad()+"");
        ViewHolder.prize.setText(products.get(i).getPrice_product()+"");


    }




    public interface OnItemClickListener {

        void onItemClick(Product item);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}