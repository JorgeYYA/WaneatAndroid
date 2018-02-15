package florida.com.waneat.Adapters;

/**
 * Created by JorgeYYA on 12/02/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.Models.Order;
import florida.com.waneat.R;
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
import florida.com.waneat.R;

/**
 * Created by JorgeYYA on 08/02/2018.
 */


public class AdapterCreditCards extends RecyclerView.Adapter<AdapterCreditCards.ClaseViewHolder>{

    public static class ClaseViewHolder extends RecyclerView.ViewHolder {

        TextView number;

        ClaseViewHolder(View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);

        }

        public void bind(final CreditCard item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {

                    listener.onItemClick(item);


                }

            });

        }

    }



    private final OnItemClickListener listener;
    ArrayList<CreditCard> cards;

    public AdapterCreditCards(ArrayList<CreditCard> cards, OnItemClickListener listener){
        this.cards = cards;
        this.listener = listener;
    }



    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public ClaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.credit_cards, viewGroup, false);
        ClaseViewHolder pvh = new ClaseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ClaseViewHolder ViewHolder, final int i) {




        ViewHolder.bind(cards.get(i), listener);

        int numProd;

        String cardNumber = cards.get(i).getCreditCardNumber();

        ViewHolder.number.setText(cardNumber.substring(0,4)+" **** **** "+cardNumber.substring(12,16));

    }




    public interface OnItemClickListener {

        void onItemClick(CreditCard item);

    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}