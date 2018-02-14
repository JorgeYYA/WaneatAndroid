package florida.com.waneat.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.R;

/**
 * Created by 2dam on 13/02/2018.
 */

public class AdapterTarjetas extends RecyclerView.Adapter<AdapterTarjetas.ViewHolder> {

    private ArrayList<CreditCard> tarjetas;

    public AdapterTarjetas(ArrayList<CreditCard> tarjetas) {
        this.tarjetas = tarjetas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjetas_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String creditCardNumber = tarjetas.get(position).getCreditCardNumber();
        String creditCardHolderName = tarjetas.get(position).getCreditCardHolderName();

        holder.creditCardNumber.setText(creditCardNumber);
        holder.creditCardHolderName.setText(creditCardHolderName);
    }

    @Override
    public int getItemCount() {
        return tarjetas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView creditCardNumber, creditCardHolderName;
        private CheckBox checkTarjeta;
        public ViewHolder(View v) {
            super(v);
            creditCardNumber = (TextView) v.findViewById(R.id.creditCardNumber);
            creditCardHolderName = (TextView) v.findViewById(R.id.creditCardHolderName);
            checkTarjeta = (CheckBox) v.findViewById(R.id.checkTarjeta);
        }
    }

}