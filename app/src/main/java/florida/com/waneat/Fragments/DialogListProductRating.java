package florida.com.waneat.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import florida.com.waneat.R;

public class DialogListProductRating extends android.support.v4.app.DialogFragment {

    private TextView textCancelar, textAceptar;
    private RatingBar ratingBar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_list_product_rating, container,
                false);

        //getDialog().setTitle("ELIGE LA PUNTUACIÓN");
        // Do something else

        textCancelar = (TextView) rootView.findViewById(R.id.textCancelar);
        textAceptar = (TextView) rootView.findViewById(R.id.textAceptar);
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);

        textCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        textAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                Log.d("estrellas", "puntuacion: "+ratingBar.getRating());
            }
        });

        return rootView;
    }
}

