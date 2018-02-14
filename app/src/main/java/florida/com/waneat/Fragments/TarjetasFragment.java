package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterItemList;
import florida.com.waneat.Adapters.AdapterTarjetas;
import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.R;


public class TarjetasFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText number,month,year,cvc,holder;
    private RelativeLayout cardFront, cardBack;
    private Button nextButton;

    private ArrayList<CreditCard> tarjetas = new ArrayList<CreditCard>();
    private AdapterItemList adapter;

    private boolean isFront = false;

    private OnFragmentInteractionListener mListener;


    public TarjetasFragment() {
        // Required empty public constructor
    }

    public static TarjetasFragment newInstance() {
        TarjetasFragment fragment = new TarjetasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tarjetas, container, false);

        tarjetas.add(new CreditCard("6567","David","e","e",4));
        tarjetas.add(new CreditCard("1243124","Sergio","e","e",4));
        mListener.hideFloatingActionButton();

        this.number = v.findViewById(R.id.creditCardNumber);
        this.cvc = v.findViewById(R.id.creditCardCVC);
        this.month = v.findViewById(R.id.creditCardMonth);
        this.year = v.findViewById(R.id.creditCardMonthYear);
        this.holder = v.findViewById(R.id.creditCardHolderName);
        this.cardBack = v.findViewById(R.id.backCard);
        this.cardFront = v.findViewById(R.id.frontCard);
        this.nextButton = v.findViewById(R.id.guardarCredit);

        getActivity().setTitle("Tus tarjetas");

        //METHODS
        toggleCard();


        RecyclerView rv = (RecyclerView)v.findViewById(R.id.recycler_tarjetas);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        rv.setLayoutManager(llm);
        AdapterTarjetas adapter = new AdapterTarjetas(tarjetas);
        rv.setAdapter(adapter);

        return v;
    }

    private void toggleCard(){
        this.cardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardFront.setVisibility(View.GONE);
                cardBack.setVisibility(View.VISIBLE);
                checkButton();
                isFront = true;
                Log.d("Main", "onClick:"+isFront);

            }
        });

        this.cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardFront.setVisibility(View.VISIBLE);
                cardBack.setVisibility(View.GONE);
                checkButton();
                isFront = false;
                Log.d("Main", "onClick:"+isFront);

            }
        });

        this.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isFront){
                   Log.d("Main", "onClick: "+"Guardamos los datos");
                   guardarDatos();
               }else{
                   Log.d("Main", "onClick: pasamos");
                   cardFront.setVisibility(View.GONE);
                   cardBack.setVisibility(View.VISIBLE);
                   checkButton();
                   isFront = true;
               }
            }
        });
    }

    private void checkButton(){
        if(!isFront){
            //estamos en el back por lo tanto guardamos
            this.nextButton.setText("GUARDAR");
        }else{
            this.nextButton.setText("SIGUIENTE");
        }
    }

    private void guardarDatos(){
        CreditCard card = new CreditCard(
                this.number.getText().toString(),
                this.holder.getText().toString(),
                this.month.getText().toString(),
                this.year.getText().toString(),
                Integer.parseInt(this.cvc.getText().toString()));
        Log.d("TAG", "guardarDatos: "+card.toString());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void showFloatingActionButton();
        void hideFloatingActionButton();
    }
}
