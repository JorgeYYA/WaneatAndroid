package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterItemList;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

public class ListProductFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private AdapterItemList productAdapter;

    public ListProductFragment() {
    }

    public static ListProductFragment newInstance() {
        ListProductFragment fragment = new ListProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_list_product, container, false);

        mListener.showFloatingActionButton();

        String idRestaurante = "";

        if(getArguments() != null){
            idRestaurante = getArguments().getString("qr");
            Toast.makeText(getContext(), idRestaurante, Toast.LENGTH_SHORT).show();
        }

        getActivity().setTitle("Waneat");

        TextView tituloRestaurante = v.findViewById(R.id.tituloRestaurante);
        TextView direccionRestaurante = v.findViewById(R.id.direccionRestaurante);
        RatingBar ratingRestaurante = v.findViewById(R.id.ruleRatingBar);

        //Incluimos la info del restaurante
        tituloRestaurante.setText("Restaaurante");
        direccionRestaurante.setText("Calle Alginet");
        ratingRestaurante.setRating(3);


        ratingRestaurante.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    DialogListProductRating ratingDialog = new DialogListProductRating();
                    // Show Alert DialogFragment
                    ratingDialog.show(getFragmentManager(), "Alert Dialog Fragment");
                }
                return true;
            }
        });


        this.recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        productAdapter = new AdapterItemList(mListener.getProductos(), new AdapterItemList.RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                mListener.verProducto(position);
            }
        });

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        return v;
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        ArrayList<Product> getProductos();
        void verProducto(int position);
        void showFloatingActionButton();
        void hideFloatingActionButton();

    }


}
