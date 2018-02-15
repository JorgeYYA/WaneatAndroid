package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import florida.com.waneat.Adapters.AdapterItemList;
import florida.com.waneat.Adapters.PhotoGalleryPager;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.Rating;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.R;
import me.relex.circleindicator.CircleIndicator;

public class ListProductFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private AdapterItemList productAdapter;
    private Restaurant restaurant;
    private Context context;

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

        TextView tituloRestaurante = v.findViewById(R.id.tituloRestaurante);
        TextView direccionRestaurante = v.findViewById(R.id.direccionRestaurante);
        RatingBar ratingRestaurante = v.findViewById(R.id.ruleRatingBar);


        this.restaurant = this.mListener.getRestauranteSelected();



        //Incluimos la info del restaurante
        tituloRestaurante.setText(this.restaurant.getNameRestaurant());
        direccionRestaurante.setText(this.restaurant.getAddressRestaurant());
        //media del ratings de los restaurantes
        List<Rating> ratings= this.restaurant.getRatings();
        int count = 0;
        float media = 0;
        if(ratings != null){
            for (Rating rate: ratings) {
                count++;
                media += rate.getRate();
            }
            ratingRestaurante.setRating(media/count);
        }


        // Ion.with(fotoRestaurante).load(this.restaurant.getImages().get(0).getImageUrl());

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        PhotoGalleryPager adapter = new PhotoGalleryPager(getContext(), restaurant.getImages());

        if (viewPager != null) {
            CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator_default);
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);
            adapter.registerDataSetObserver(indicator.getDataSetObserver());
        }

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

        getActivity().setTitle("Waneat");


        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            this.context = context;

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
        void verProducto(int position);
        void showFloatingActionButton();
        void hideFloatingActionButton();

    }


}
