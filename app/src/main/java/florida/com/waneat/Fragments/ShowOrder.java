package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import florida.com.waneat.Adapters.AdapterProductList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

public class ShowOrder extends Fragment {


    private ArrayList<String> imagen;
    private Order order;
    private ArrayList<Product> products;
    private RecyclerView recyclerProd;
    private TextView resName,date,totalPrize;

    private OnFragmentInteractionListener mListener;

    public ShowOrder() {

    }


    public static ShowOrder newInstance(Order order) {
        ShowOrder fragment = new ShowOrder();
        Bundle args = new Bundle();
        args.putParcelable("PEDIDO", order);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           order = (Order) getArguments().getParcelable("PEDIDO");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_order, container, false);

        imagen = new ArrayList<String>();



        mListener.hideFloatingActionButton();

        resName = (TextView) v.findViewById(R.id.res_name);
        date = (TextView) v.findViewById(R.id.order_date);
        totalPrize = (TextView) v.findViewById(R.id.prize);

        products = order.getProducts();

        recyclerProd = (RecyclerView) v.findViewById(R.id.product_list);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        recyclerProd.setLayoutManager(llm);


        recyclerProd.setAdapter(new AdapterProductList(products, new AdapterProductList.OnItemClickListener() {

            @Override
            public void onItemClick(Product item) {

               Toast.makeText(getActivity(), item.getNameProduct(), Toast.LENGTH_SHORT).show();


            }


        }));



        recyclerProd.invalidate();


        //Recoge la primera imagen de cada producto del pedido
        recoverImages();


        //Muestra por pantalla los datos del pedido
        resName.setText(order.getResName());
        date.setText(order.getDate());
        totalPrize.setText(order.getTotal()+"");


        //TODO: Cambiar a order num
        getActivity().setTitle("Pedido Num: 1");


        return v;
    }


    public void recoverImages(){

        for(int i = 0;i<order.getProducts().size();i++){
            if(order.getProducts().get(i).getImages().get(0) != null) {
                imagen.add(order.getProducts().get(i).getImages().get(i).getImageUrl());
            }

        }

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public interface OnFragmentInteractionListener {
        void showFloatingActionButton();
        void hideFloatingActionButton();
    }

    
}
