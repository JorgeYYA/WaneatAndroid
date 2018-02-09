package florida.com.waneat.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterOrderList;
import florida.com.waneat.Adapters.AdapterProductList;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;


public class ShowOrder extends Fragment {


    private String mParam1;
    private String mParam2;

    private ArrayList<Integer> imagen;

    Order order;

    ArrayList<Product> products;

    RecyclerView recyclerProd;

    ImageSwitcher imageSwitcher;

    TextView resName,date,totalPrize;

    private OnFragmentInteractionListener mListener;

    public ShowOrder() {

    }


    public static ShowOrder newInstance(Order order) {
        ShowOrder fragment = new ShowOrder();
        Bundle args = new Bundle();
        args.putSerializable("PEDIDO", order);
        //args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           order = (Order) getArguments().getSerializable("PEDIDO");
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_order, container, false);



        imagen = new ArrayList<Integer>();




        imageSwitcher = (ImageSwitcher) v.findViewById(R.id.image_switcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(getActivity());
            }
        });

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

               Toast.makeText(getActivity(), item.getNombre(), Toast.LENGTH_SHORT).show();

            }


        }));




        recyclerProd.invalidate();

        imagen = order.getProducts().get(0).getImagen();


        resName.setText(order.getResName());
        date.setText(order.getDate());
        totalPrize.setText("Total: "+order.getTotal()+"€");


        imageSwitcher.setImageResource(imagen.get(0));


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

        super.onAttach(activity);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    
}
