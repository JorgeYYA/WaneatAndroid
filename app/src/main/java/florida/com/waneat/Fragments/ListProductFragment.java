package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    public AdapterItemList.RecyclerViewOnItemClickListener itemListener;
    private Context context;
    private FloatingActionButton fab_cat, fab_carne, fab_pescado, fab_pasta, fab_bebida;
    boolean isOpen = false;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;

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

        getFabs(v);

        // Ion.with(fotoRestaurante).load(this.restaurant.getImages().get(0).getImageUrl());

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        PhotoGalleryPager adapter = new PhotoGalleryPager(getContext(), restaurant.getImages());

        if (viewPager != null) {
            CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator_default);
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);
            adapter.registerDataSetObserver(indicator.getDataSetObserver());
        }


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



        itemListener = new AdapterItemList.RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                mListener.verProducto(position);
            }

        };

        productAdapter = new AdapterItemList(mListener.getProductos(), itemListener);

        this.recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        getActivity().setTitle("Waneat");
        mListener.changeColorToolbar(false);


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

    private void animateFab() {
        if(isOpen) {
            fab_cat.startAnimation(rotateForward);
            fab_carne.startAnimation(fabClose);
            fab_pescado.startAnimation(fabClose);
            fab_pasta.startAnimation(fabClose);
            fab_bebida.startAnimation(fabClose);
            fab_carne.setClickable(false);
            fab_pescado.setClickable(false);
            fab_pasta.setClickable(false);
            fab_bebida.setClickable(false);
            isOpen = false;
        }
        else
        {
            fab_cat.startAnimation(rotateBackward);
            fab_carne.startAnimation(fabOpen);
            fab_pescado.startAnimation(fabOpen);
            fab_pasta.startAnimation(fabOpen);
            fab_bebida.startAnimation(fabOpen);
            fab_carne.setClickable(true);
            fab_pescado.setClickable(true);
            fab_pasta.setClickable(true);
            fab_bebida.setClickable(true);
            isOpen = true;
        }

    }


    public void getFabs(View v){
        fab_cat = (FloatingActionButton) v.findViewById(R.id.fab_cat);
        fab_carne = (FloatingActionButton) v.findViewById(R.id.fab_carne);
        fab_pescado = (FloatingActionButton) v.findViewById(R.id.fab_pescado);
        fab_pasta = (FloatingActionButton) v.findViewById(R.id.fab_pasta);
        fab_bebida = (FloatingActionButton) v.findViewById(R.id.fab_bebida);


        fabOpen = AnimationUtils.loadAnimation(getContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);



        fab_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                productAdapter.filter("");
            }
        });

        fab_carne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                productAdapter.filter("Carne");

            }
        });

        fab_pescado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                productAdapter.filter("Pescado");

            }
        });

        fab_pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                productAdapter.filter("Pasta");

            }
        });

        fab_bebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                productAdapter.filter("Bebida");
            }
        });
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        List<Product> getProductos();
        Restaurant getRestauranteSelected();
        void changeColorToolbar(boolean dark);
        void verProducto(int position);
        void showFloatingActionButton();
        void hideFloatingActionButton();

    }


}
