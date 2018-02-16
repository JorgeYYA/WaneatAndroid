package florida.com.waneat.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import florida.com.waneat.Adapters.AdapterCartItem;
import florida.com.waneat.Adapters.AdapterCreditCards;
import florida.com.waneat.Adapters.AdapterFinalizarCompra;
import florida.com.waneat.Adapters.AdapterProductList;
import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Models.User;
import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;

import static android.content.Context.MODE_PRIVATE;

public class DialogFragment extends android.support.v4.app.DialogFragment{

    public CestaInterface mListener;
    private RecyclerView recyclerView, recyclerCards;
    private TextView cestaTotal, tarjetaCredito;
    private AdapterCartItem adapter;
    private AdapterFinalizarCompra adapterCompra;
    private AdapterCreditCards adapterCards;
    private Button checkoutButton, buttonIntroducirTarj, tramitarPedido, addCard;
    private LinearLayout layoutEmpty;
    private GridLayout layoutCards;
    private User user = new User();
    private Restaurant restaurant = new Restaurant();
    private ImageView mini;

    DatabaseReference bbdd;

    private int cardPos = 0;

    private ArrayList<Product> cesta = new ArrayList<Product>();


    private ArrayList<CreditCard> cards = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dialog_fragment, container, false);
        this.cesta = mListener.getProductosCesta();
        this.recyclerView = rootView.findViewById(R.id.cestaRecyclerView);
        this.recyclerCards = rootView.findViewById(R.id.recycler_cards);
        this.cestaTotal = rootView.findViewById(R.id.cestaTotal);
        this.checkoutButton = rootView.findViewById(R.id.checkoutButton);
        this.layoutEmpty = rootView.findViewById(R.id.layout_empty);
        this.layoutCards = rootView.findViewById(R.id.layout_cards);
        this.tarjetaCredito = rootView.findViewById(R.id.card_number);
        this.tramitarPedido = rootView.findViewById(R.id.tramitar);
        this.addCard = rootView.findViewById(R.id.add_card);
        this.mini = rootView.findViewById(R.id.miniatura);

        bbdd = FirebaseDatabase.getInstance().getReference("pedidos");

        //cargamos los precios
        reloadPrecios();

        adapter = new AdapterCartItem(getContext(), cesta, new AdapterCartItem.BtnClickListener() {

            @Override
            public void onAddProducts(int position) {
                mListener.addProduct(position);
                adapter.notifyDataSetChanged();
                reloadPrecios();
            }
            @Override
            public void onRemoveProducts(int position) {
                mListener.removeProduct(position);
                adapter.notifyDataSetChanged();
                reloadPrecios();
            }
        });

        user = Preferences.gsonToUser(getContext());

        restaurant = Preferences.gsonToRestaurant(getContext());

        CreditCard cc1 = new CreditCard("4242424242424242","111","111","111",1);
        CreditCard cc2 = new CreditCard("0123456789123456","111","111","111",1);

        cc1.setId(0);
        cc2.setId(1);

        cards.add(cc1);

        cards.add(cc2);

        if(cards != null) {


            SharedPreferences prefs = getActivity().getSharedPreferences("preferences", MODE_PRIVATE);

            int cardId = prefs.getInt("preferred_card",0);

            tarjetaCredito.setText(cards.get(cardId).getCreditCardNumber().substring(0, 4) + " **** **** " + cards.get(cardId).getCreditCardNumber().substring(12, 16));


        }

        recyclerView.setVisibility(View.VISIBLE);
        layoutCards.setVisibility(View.GONE);
        tramitarPedido.setVisibility(View.GONE);
        checkoutButton.setVisibility(View.VISIBLE);
        addCard.setVisibility(View.GONE);

        if(cesta.isEmpty()){

            layoutEmpty.setVisibility(View.VISIBLE);

        }else{

            layoutEmpty.setVisibility(View.GONE);

        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        recyclerCards.setLayoutManager(mLayoutManager2);
        recyclerCards.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recyclerCards.setItemAnimator(new DefaultItemAnimator());

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                buttonIntroducirTarj = (Button) rootView.findViewById(R.id.buttonIntroducirTarj);

                if(!cesta.isEmpty()){

                    layoutCards.setVisibility(View.VISIBLE);
                    tramitarPedido.setVisibility(View.VISIBLE);
                    checkoutButton.setVisibility(View.GONE);

                    adapterCompra = new AdapterFinalizarCompra(cesta);


                    recyclerView.setAdapter(adapterCompra);
                   // recyclerCards.setAdapter(adapterCompra);

                }

                buttonIntroducirTarj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        layoutCards.setVisibility(View.GONE);
                        tramitarPedido.setVisibility(View.GONE);
                        checkoutButton.setVisibility(View.GONE);
                        addCard.setVisibility(View.VISIBLE);

                       // adapterCompra = new AdapterCreditCards(cards);
                        recyclerView.setAdapter(new AdapterCreditCards(cards, new AdapterCreditCards.OnItemClickListener() {

                            @Override
                            public void onItemClick(CreditCard item) {


                                layoutCards.setVisibility(View.VISIBLE);
                                tramitarPedido.setVisibility(View.VISIBLE);
                                checkoutButton.setVisibility(View.GONE);
                                addCard.setVisibility(View.GONE);

                                adapterCompra = new AdapterFinalizarCompra(cesta);

                                recyclerView.setAdapter(adapterCompra);

                                SharedPreferences.Editor prefs = getActivity().getSharedPreferences("preferences", MODE_PRIVATE).edit();

                                prefs.putInt("preferred_card", item.getId());

                                prefs.apply();

                                tarjetaCredito.setText(item.getCreditCardNumber().substring(0, 4) + " **** **** " + item.getCreditCardNumber().substring(12, 16));



                            }


                        }));

                       // recyclerView.setAdapter(adapterCompra);


                    }
                });

            }
        });



        tramitarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tramitarPedido();

                getDialog().dismiss();

            }
        });

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, TarjetasFragment.newInstance()).addToBackStack(null);
                ft.commit();

                getDialog().dismiss();

            }
        });



        //Method related with this dialog
        return rootView;
    }


    public void tramitarPedido(){


        Date d = new Date();

        CharSequence s  = DateFormat.format("dd/MM/yyyy", d.getTime());


        int idUsuario = user.getId();

        //PARTIALLY HARDCODED
        Order o = new Order(idUsuario,cesta, s+"", restaurant.getNameRestaurant(), Double.valueOf(String.valueOf(mListener.getCestaPrice())));



        String clave = bbdd.push().getKey();

        bbdd.child("pedido_"+clave).setValue(o);

        Toast to = Toast.makeText(getActivity(), "El pedido ha sido tramitado correctamente", Toast.LENGTH_SHORT);

        to.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);

        to.show();

        for(int i = 0;i<cesta.size();i++){

            cesta.get(i).setCantidad(1);

        }

        cesta.clear();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CestaInterface) {
            mListener = (CestaInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void reloadPrecios(){
        DecimalFormat df = new DecimalFormat("#.00");


        this.cestaTotal.setText(df.format(Double.valueOf(String.valueOf(mListener.getCestaPrice())))+getResources().getString(R.string.badge));
    }


    public interface CestaInterface {
        ArrayList<Product> getProductosCesta();
        double getCestaPrice();
        void addProduct(int position);
        void removeProduct(int position);
    }

}
