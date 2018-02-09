package florida.com.waneat.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterCartItem;
import florida.com.waneat.Adapters.AdapterFinalizarCompra;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

public class DialogFragment extends android.support.v4.app.DialogFragment{

    public CestaInterface mListener;
    private RecyclerView recyclerView;
    private TextView cestaTotal;
    private AdapterCartItem adapter;
    private AdapterFinalizarCompra adapterCompra;
    private Button checkoutButton, buttonIntroducirTarj;

    private ArrayList<Product> cesta = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().setTitle("Cesta");
        this.cesta = mListener.getProductosCesta();
        this.recyclerView = rootView.findViewById(R.id.cestaRecyclerView);
        this.cestaTotal = rootView.findViewById(R.id.cestaTotal);
        this.checkoutButton = rootView.findViewById(R.id.checkoutButton);

        //cargamos los precios
        reloadPrecios();

        adapter = new AdapterCartItem(cesta, new AdapterCartItem.BtnClickListener() {

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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        checkoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                buttonIntroducirTarj = (Button) rootView.findViewById(R.id.buttonIntroducirTarj);


                adapterCompra = new AdapterFinalizarCompra(cesta);
                recyclerView.setAdapter(adapterCompra);

                buttonIntroducirTarj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment, TarjetasFragment.newInstance()).addToBackStack(null);
                        ft.commit();
                    }
                });

            }
        });

        //Method related with this dialog
        return rootView;
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
        this.cestaTotal.setText(String.valueOf(mListener.getCestaPrice()));
    }


    public interface CestaInterface {
        ArrayList<Product> getProductosCesta();
        double getCestaPrice();
        void addProduct(int position);
        void removeProduct(int position);
    }

}
