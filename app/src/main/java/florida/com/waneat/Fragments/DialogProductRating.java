package florida.com.waneat.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import florida.com.waneat.R;


public class DialogProductRating extends android.support.v4.app.DialogFragment {

    private TextView textCancelar1, textAceptar1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_product_rating, container,
                false);

        //getDialog().setTitle("ELIGE LA PUNTUACIÓN");
        // Do something else

        textCancelar1 = (TextView) rootView.findViewById(R.id.textCancelar1);
        textAceptar1 = (TextView) rootView.findViewById(R.id.textAceptar1);

        textCancelar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        textAceptar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
}

