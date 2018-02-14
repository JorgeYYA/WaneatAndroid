package florida.com.waneat.Fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Adapters.AdapterOrderList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;


public class OrderList extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<Order> orders;

    ArrayList<Integer> imagen;

    ArrayList<Product> products;

    RecyclerView recyclerOrders;

    double total;

    LinearLayout empty;

    TextView info;

    private InterfaceOrder interfaz;


    DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference();

    int idUser=0;

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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_list, container, false);

        empty = (LinearLayout) v.findViewById(R.id.empty_list);
        info = (TextView) v.findViewById(R.id.info);

        info.setText("  Cargando...\n(Podría tardar un rato)");

        products = new ArrayList<Product>();
        orders = new ArrayList<Order>();

        recyclerOrders = (RecyclerView)v.findViewById(R.id.recycler_orders);

        interfaz.hideFloatingActionButton();

        imagen = new ArrayList<>();
        imagen.add(R.drawable.plato1);
        imagen.add(R.drawable.plato2);


        if (!MainActivity.verificaConexion(getActivity())) {
            info.setText("No se ha podido conectar, \ncomprueba tu conexión a internet");
        }else {

            recuperaDatos();

        }

        return v;
    }

    public void recuperaDatos(){

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Order order = ds.getValue(Order.class);

                    if (order.getUserId() == idUser) {

                        orders.add(order);

                    }

                }

                LinearLayoutManager llm = new LinearLayoutManager(getActivity());

                recyclerOrders.setLayoutManager(llm);


                recyclerOrders.setAdapter(new AdapterOrderList(orders, new AdapterOrderList.OnItemClickListener() {

                    @Override
                    public void onItemClick(Order item) {

                        interfaz.interfaceOrder(item);

                    }

                }));

                recyclerOrders.invalidate();


                if (orders.size() == 0) {


                    info.setText("No hay pedidos registrados");
                    empty.setVisibility(View.VISIBLE);
                    recyclerOrders.setVisibility(View.GONE);

                } else {

                    empty.setVisibility(View.GONE);
                    recyclerOrders.setVisibility(View.VISIBLE);


                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                info.setText("No se ha podido conectar, comprueba tu conexión a internet");

            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        interfaz = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListProductFragment.OnFragmentInteractionListener) {
            interfaz = (InterfaceOrder) context;
            getActivity().setTitle("Listado de pedidos");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public double sumaPrecio(ArrayList<Product> products){

        double total = 0;

        for(int i=0;i<products.size();i++){

            for(int i1=0;i1<products.get(i).getCantidad();i1++) {
                total = total + products.get(i).getPriceProduct();
            }

        }

        return total;
    }

    public interface InterfaceOrder{
        void interfaceOrder(Order order);
        void showFloatingActionButton();
        void hideFloatingActionButton();

    }
}
