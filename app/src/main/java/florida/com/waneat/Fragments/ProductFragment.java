package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import florida.com.waneat.Adapters.PhotoGalleryPagerAdapter;
import florida.com.waneat.Models.Product;
import florida.com.waneat.Models.Rating;
import florida.com.waneat.Models.Rating_;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.R;
import me.relex.circleindicator.CircleIndicator;


public class ProductFragment extends Fragment {


    private Product pro;
    private TextView price, name, desc,categoriaProducto, ratingNumberRestaurant;
    private Button add;
    private OnFragmentInteractionListener mListener;
    private RatingBar ratingBar;

    public ProductFragment() {

    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_product, container, false);

        price = (TextView) v.findViewById(R.id.p_price);
        name = (TextView) v.findViewById(R.id.p_name);
        desc = (TextView) v.findViewById(R.id.p_desc);
        add = (Button) v.findViewById(R.id.p_add);
        categoriaProducto = (TextView) v.findViewById(R.id.categoriaProducto);
        ratingNumberRestaurant = (TextView) v.findViewById(R.id.ratingNumberRestaurant);
        ratingBar  = (RatingBar) v.findViewById(R.id.ratingRestaurant);

        pro = mListener.getProductoSelected();

        //Añadimos 1 por defecto
        pro.setCantidad(1);

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        PhotoGalleryPagerAdapter adapter = new PhotoGalleryPagerAdapter(getContext(), pro.getImages());

        if (viewPager != null) {
            CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator_default);
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);
            adapter.registerDataSetObserver(indicator.getDataSetObserver());

        }

        showData();

        getActivity().setTitle(name.getText().toString());


        //Listener de "Añadir a la cesta"
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addToCart(pro);
                Toast.makeText(getContext(), "Añadido: "+pro.getNameProduct()+" a la cesta correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }


    public void showData(){

        name.setText(pro.getNameProduct());
        price.setText(pro.getPriceProduct()+""+getResources().getText(R.string.badge));
        desc.setText(pro.getDescriptionProduct());
        categoriaProducto.setText(pro.getCategoryProduct());
        List<Rating_> ratings= this.pro.getRatings();
        int count = 0;
        float media = 0;
        for (Rating_ rate: ratings) {
            count++;
            media += rate.getRate();
        }
        float mediaFinal = media/count;
        ratingNumberRestaurant.setText(String.valueOf(mediaFinal));
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(mediaFinal);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        Product getProductoSelected();
        void addToCart(Product prod);
        void showFloatingActionButton();
        void hideFloatingActionButton();
    }
}




