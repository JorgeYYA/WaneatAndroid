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

import java.text.DecimalFormat;
import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Fragments.OrderList;
import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.Models.Order;
import florida.com.waneat.R;

/**
 * Created by JorgeYYA on 08/02/2018.
 */


public class AdapterTarjetas extends RecyclerView.Adapter<AdapterTarjetas.ClaseViewHolder>{

    private final OnItemLongClickListener listener;
    private ArrayList<CreditCard> tarjetas;

    public static class ClaseViewHolder extends RecyclerView.ViewHolder {

        TextView creditCardNumber, creditCardHolderName;

        ClaseViewHolder(View itemView) {
            super(itemView);

            creditCardNumber = (TextView) itemView.findViewById(R.id.creditCardNumber);
            creditCardHolderName = (TextView) itemView.findViewById(R.id.creditCardHolderName);

        }

        public void bind(final CreditCard item, final OnItemLongClickListener listener) {


            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override public boolean onLongClick(View v) {

                    listener.onItemLongClick(item);

                    return true;

                }

            });

        }

    }




    public AdapterTarjetas(ArrayList<CreditCard> tarjetas, OnItemLongClickListener listener){
        this.tarjetas = tarjetas;
        this.listener = listener;
    }



    @Override
    public int getItemCount() {
        return (tarjetas == null) ? 0 : tarjetas.size();
    }

    @Override
    public ClaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tarjetas_list, viewGroup, false);
        ClaseViewHolder pvh = new ClaseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ClaseViewHolder ViewHolder, final int i) {


        ViewHolder.bind(tarjetas.get(i), listener);


        String creditCardNumber = tarjetas.get(i).getCreditCardNumber();
        String creditCardHolderName = tarjetas.get(i).getCreditCardHolderName();

        ViewHolder.creditCardNumber.setText(creditCardNumber.substring(0, 4) + " **** **** " + creditCardNumber.substring(12, 16));
        ViewHolder.creditCardHolderName.setText(creditCardHolderName);
    }




    public interface OnItemLongClickListener {

        boolean onItemLongClick(CreditCard item);

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}