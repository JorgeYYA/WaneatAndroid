package florida.com.waneat.Adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Fragments.OrderList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.R;

/**
 * Created by JorgeYYA on 08/02/2018.
 */


public class AdapterOrderList extends RecyclerView.Adapter<AdapterOrderList.ClaseViewHolder>{

    private final OnItemClickListener listener;
    ArrayList<Order> orders = new ArrayList<>();
    private Context context;

    public static class ClaseViewHolder extends RecyclerView.ViewHolder {

        TextView resName, date, prodList, totalPrize;
        ViewPager viewPager;

        ClaseViewHolder(View itemView) {
            super(itemView);

            resName = itemView.findViewById(R.id.res_name);
            date = itemView.findViewById(R.id.date);
            prodList = itemView.findViewById(R.id.prod_list);
            totalPrize = itemView.findViewById(R.id.prize);
            viewPager = itemView.findViewById(R.id.viewPagerOrder);

        }

        public void bind(final Order item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }

    }




    public AdapterOrderList(ArrayList<Order> orders, Context context, OnItemClickListener listener){
        this.orders = orders;
        this.listener = listener;
        this.context = context;
    }



    @Override
    public int getItemCount() {
        return (orders == null) ? 0 : orders.size();
    }

    @Override
    public ClaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list, viewGroup, false);
        ClaseViewHolder pvh = new ClaseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ClaseViewHolder ViewHolder, final int i) {


        ViewHolder.bind(orders.get(i), listener);

        int numProd;

        String list = "";

        DecimalFormat df = new DecimalFormat("#.00€");


        ViewHolder.resName.setText(orders.get(i).getResName());
        ViewHolder.date.setText(orders.get(i).getDate());
        ViewHolder.totalPrize.setText(df.format(orders.get(i).getTotal()));
        PhotoGalleryPagerAdapter adapter = new PhotoGalleryPagerAdapter(context, orders.get(i).getProducts().get(0).getImages());
        ViewHolder.viewPager.setAdapter(adapter);

        if(orders.get(i).getProducts() != null){
            if (orders.get(i).getProducts().size()>=3){
                numProd = 3;

            }else{

                numProd = orders.get(i).getProducts().size();

            }

            for(int i1=0;i1<numProd;i1++) {

                if (i1 == numProd-1) {

                    list = list + orders.get(i).getProducts().get(i1).getNameProduct();

                } else {

                    list = list + orders.get(i).getProducts().get(i1).getNameProduct() + ", ";
                }
            }

            if (orders.get(i).getProducts().size()>3){

                list = list +" ...";

            }
        }




        ViewHolder.prodList.setText(list);
    }




    public interface OnItemClickListener {
        void onItemClick(Order item);
    }

     @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}