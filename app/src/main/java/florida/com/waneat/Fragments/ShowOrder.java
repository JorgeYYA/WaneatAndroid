package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.text.DecimalFormat;
import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Adapters.AdapterProductList;
import florida.com.waneat.Adapters.PhotoGalleryPagerAdapter;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;
import me.relex.circleindicator.CircleIndicator;

import static florida.com.waneat.Fragments.ShowOrder.size;

public class ShowOrder extends Fragment {

    private Order order;
    private ArrayList<Product> products;
    private RecyclerView recyclerProd;
    private TextView resName,date,totalPrize;
    private ImageView imagenOrder;
    private OnFragmentInteractionListener mListener;
    static int size;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_order, container, false);


        mListener.hideFloatingActionButton();

        resName = (TextView) v.findViewById(R.id.res_name);
        date = (TextView) v.findViewById(R.id.order_date);
        totalPrize = (TextView) v.findViewById(R.id.prize);
        imagenOrder = (ImageView) v.findViewById(R.id.imagen);
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


        DecimalFormat df = new DecimalFormat("#.00");

        //Muestra por pantalla los datos del pedido
        resName.setText(order.getResName());
        date.setText(order.getDate());
        totalPrize.setText(df.format(order.getTotal())+"");
        //ESTO PETA
        Ion.with(imagenOrder).load(order.getProducts().get(0).getImages().get(0).getImageUrl());
        Log.d("ShowOrder", "onCreateView: "+order.getProducts().get(0).getNameProduct());

        getActivity().setTitle("Pedido Num: "+order.getId());

        return v;
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
