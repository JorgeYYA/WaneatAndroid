package florida.com.waneat.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterTarjetas;
import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;


public class TarjetasFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText number,month,year,cvc,holder;
    private RelativeLayout cardFront, cardBack;
    private Button nextButton;
    private TextView emptyList;
    private ArrayList<CreditCard> tarjetas = new ArrayList<>();
    private boolean isFront = false;

    private OnFragmentInteractionListener mListener;

    RecyclerView rv;


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


        mListener.changeColorToolbar(true);


        emptyList = (TextView) v.findViewById(R.id.empty_list);
        rv = (RecyclerView)v.findViewById(R.id.recycler_tarjetas);

        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        rv.setLayoutManager(llm);

        cargarDatos();


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
            this.nextButton.setText(getResources().getString(R.string.guardar));
        }else{
            this.nextButton.setText(getResources().getString(R.string.guardarCredit));
        }
    }

    private void guardarDatos(){


        if(this.number.getText().toString().length()==16) {

            CreditCard card = new CreditCard(
                    this.number.getText().toString(),
                    this.holder.getText().toString(),
                    this.month.getText().toString(),
                    this.year.getText().toString(),
                    Integer.parseInt(this.cvc.getText().toString()));

            if(card != null){
                Log.d("TarjetasFragment", "guardarDatos: "+card.getCreditCardNumber());
                Log.d("TarjetasFragment", "guardarDatos: "+card.getCreditCardMonthDate());
                Log.d("TarjetasFragment", "guardarDatos: "+card.getCreditCardYearDate());
                Log.d("TarjetasFragment", "guardarDatos: "+card.getCreditCardCvc());
                Log.d("TarjetasFragment", "guardarDatos: "+card.getCreditCardHolderName());
                this.tarjetas.add(card);
                Preferences.creditCardToString(getContext(), Preferences.CREDIT_CARD, this.tarjetas);
                cargarDatos();
            }



        }else{

            Toast.makeText(getActivity(), getResources().getString(R.string.datosIncorrectosTarjeta), Toast.LENGTH_SHORT).show();

        }



    }

    private  void cargarDatos(){


        emptyList.setVisibility(View.GONE);

        if(Preferences.gsonToCreditCard(getContext()) != null){
            tarjetas = Preferences.gsonToCreditCard(getContext());
        }

        rv.setAdapter(new AdapterTarjetas(tarjetas, new AdapterTarjetas.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(CreditCard item) {

                borrarTarjetas(item.getId(), item.getCreditCardNumber());

                return true;

            }
        }));


        if(Preferences.gsonToCreditCard(getContext()) != null){
            if (Preferences.gsonToCreditCard(getContext()).size() == 0) {
                emptyList.setVisibility(View.VISIBLE);
            }
        }


    }

    private void borrarTarjetas(final int id, final String cardName){




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Está seguro de que desea eliminar la tarjeta "+cardName.substring(0, 4) + " **** **** " + cardName.substring(12, 16)+"?").setTitle("Eliminar");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                tarjetas.remove(id);

                fixCardsArray();

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void fixCardsArray(){

        for (int i = 0;i<tarjetas.size();i++){

            tarjetas.get(i).setId(i);

        }

        Preferences.creditCardToString(getContext(),Preferences.CREDIT_CARD,tarjetas);


        Preferences.setInt(getContext(),Preferences.PREFERRED_CREDIT_CARD,0);

        cargarDatos();



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
        void callQRActivity();
        void changeColorToolbar(boolean dark);
        void hideFloatingActionButton();
    }
}
