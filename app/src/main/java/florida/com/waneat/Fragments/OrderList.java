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
import florida.com.waneat.Models.User;
import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;


public class OrderList extends Fragment {
    private ArrayList<Order> orders = new ArrayList<>();
    private RecyclerView recyclerOrders;
    private LinearLayout empty;
    private TextView info;
    private InterfaceOrder interfaz;


    DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference();

    private User user = new User();

    public OrderList() {

    }


    public static OrderList newInstance(String param1, String param2) {
        OrderList fragment = new OrderList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_list, container, false);

        this.user =  Preferences.gsonToUser(getContext());

        empty = (LinearLayout) v.findViewById(R.id.empty_list);
        info = (TextView) v.findViewById(R.id.info);

        info.setText("  Cargando...\n(Podría tardar un rato)");


        recyclerOrders = (RecyclerView)v.findViewById(R.id.recycler_orders);

        interfaz.hideFloatingActionButton();


        user = Preferences.gsonToUser(getContext());

        bbdd = bbdd.child("pedidos");


        if (!MainActivity.verificaConexion(getActivity())) {
            info.setText("No se ha podido conectar, \ncomprueba tu conexión a internet");
        } else {

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
                    Log.d("TAG","asd");

                    if (order.getUserId() == user.getId()) {

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


    public interface InterfaceOrder{
        void interfaceOrder(Order order);
        void showFloatingActionButton();
        void hideFloatingActionButton();

    }
}
