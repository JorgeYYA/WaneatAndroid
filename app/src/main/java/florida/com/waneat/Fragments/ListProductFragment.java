package florida.com.waneat.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import florida.com.waneat.Adapters.AdapterItemList;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.Rating;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.R;
import florida.com.waneat.Services.RestaurantService;

public class ListProductFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private AdapterItemList productAdapter;
    private Restaurant restaurant;

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

        //Tenemos la id del restaurante para hacer la llamada
        if(getArguments() != null){
            idRestaurante = getArguments().getString("qr");
            Toast.makeText(getContext(), idRestaurante, Toast.LENGTH_SHORT).show();
            mListener.callApiRestaurant(Integer.parseInt(idRestaurante));
        }

        getActivity().setTitle("Waneat");

        this.restaurant = this.mListener.getRestauranteSelected();

        TextView tituloRestaurante = v.findViewById(R.id.tituloRestaurante);
        TextView direccionRestaurante = v.findViewById(R.id.direccionRestaurante);
        RatingBar ratingRestaurante = v.findViewById(R.id.ruleRatingBar);
        ImageView fotoRestaurante = v.findViewById(R.id.fotoRestaurante);

        //Incluimos la info del restaurante
        tituloRestaurante.setText(this.restaurant.getNameRestaurant());
        direccionRestaurante.setText(this.restaurant.getAddressRestaurant());
        //media del ratings de los restaurantes
        List<Rating> ratings= this.restaurant.getRatings();
        int count = 0;
        float media = 0;
        for (Rating rate: ratings) {
            count++;
            media += rate.getRate();
        }
        ratingRestaurante.setRating(media/count);

        Ion.with(fotoRestaurante).load(this.restaurant.getImages().get(0).getImageUrl());

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
        List<Product> getProductos();
        Restaurant getRestauranteSelected();
        void callApiRestaurant(int id);
        void verProducto(int position);
        void showFloatingActionButton();
        void hideFloatingActionButton();

    }


}
