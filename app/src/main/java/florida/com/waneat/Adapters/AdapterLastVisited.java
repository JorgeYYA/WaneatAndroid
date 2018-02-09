package florida.com.waneat.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Fragments.OrderList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.R;

/**
 * Created by JorgeYYA on 08/02/2018.
 */


public class AdapterLastVisited extends RecyclerView.Adapter<AdapterLastVisited.ClaseViewHolder>{

    public static class ClaseViewHolder extends RecyclerView.ViewHolder {

        TextView resName, date;
        RatingBar rate;
        ClaseViewHolder(View itemView) {
            super(itemView);

            resName = itemView.findViewById(R.id.res_name);
            date = itemView.findViewById(R.id.date);
            rate = itemView.findViewById(R.id.rating_bar);


        }

        public void bind(final Restaurant item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }

    }



    private final OnItemClickListener listener;
    ArrayList<Restaurant> restaurants;

    public AdapterLastVisited(ArrayList<Restaurant> restaurants, OnItemClickListener listener){
        this.restaurants = restaurants;
        this.listener = listener;
    }



    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    @Override
    public ClaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.last_visited, viewGroup, false);
        ClaseViewHolder pvh = new ClaseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ClaseViewHolder ViewHolder, final int i) {


        ViewHolder.bind(restaurants.get(i), listener);

        int numProd;

        String list = "";



        ViewHolder.resName.setText(restaurants.get(i).getNombre());
        ViewHolder.rate.setRating((float)restaurants.get(i).getPunctuation());
        ViewHolder.date.setText("10/02/19");

    }




    public interface OnItemClickListener {

        void onItemClick(Restaurant item);

    }














    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}