package florida.com.waneat.Fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterOrderList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;


public class OrderList extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private interfaceOrder interfaz;


    private android.app.FragmentManager fm;
    private FragmentTransaction ft;

    ArrayList<Order> orders;

    ArrayList<Integer> imagen;

    ArrayList<Product> products;

    static RecyclerView recyclerOrders;

    double total;

    private OnFragmentInteractionListener mListener;

    public OrderList() {

    }


    public static OrderList newInstance(String param1, String param2) {
        OrderList fragment = new OrderList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_list, container, false);

        products = new ArrayList<Product>();
        orders = new ArrayList<Order>();

        imagen = new ArrayList<>();
        imagen.add(R.drawable.plato1);
        imagen.add(R.drawable.plato2);

        //DEBUGGING MUY FUERTE
        Product producto = new Product(0, "spaguettis", "boloñesa, algo más", 2.0,imagen, "Sin salsa", "pasta", 3);

        Product producto2 = new Product(1, "macarrones", "boloñesa, algo más", 3.0, imagen, "Con salsa", "pasta", 2);

        Product producto3 = new Product(2, "spaguettis2", "boloñesa, algo más", 2.0,imagen, "Sin salsa", "pasta", 3);

        Product producto4 = new Product(3, "macarrones2", "boloñesa, algo más", 3.0, imagen, "Con salsa", "pasta", 2);

        products.add(producto);
        products.add(producto2);
        products.add(producto3);
        products.add(producto4);

        total = sumaPrecio(products);

        Order order = new Order(products,"10/2/2018","Restaurante Paco Mer",total);

        Order order2 = new Order(products,"10/2/2018","Restaurante Sin Trazas de Palabras Malsonantes",total);

        orders.add(order);
        orders.add(order2);

        recyclerOrders = (RecyclerView) v.findViewById(R.id.recycler_orders);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        recyclerOrders.setLayoutManager(llm);

        recyclerOrders.setAdapter(new AdapterOrderList(orders, new AdapterOrderList.OnItemClickListener() {

            @Override
            public void onItemClick(Order item) {

                interfaz.interfaceOrder(item);

            }

        }));

        recyclerOrders.invalidate();

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Activity activity) {
        interfaz = (interfaceOrder) activity;
        super.onAttach(activity);
    }

    public double sumaPrecio(ArrayList<Product> products){

        double total = 0;

        for(int i=0;i<products.size();i++){

            for(int i1=0;i1<products.get(i).getCantidad();i1++) {
                total = total + products.get(i).getPrecio();
            }

        }

        return total;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface interfaceOrder{
        public void interfaceOrder(Order order);

    }
}
