package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Adapters.PhotoGalleryPagerAdapter;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;
import me.relex.circleindicator.CircleIndicator;

import static florida.com.waneat.Fragments.ShowOrder.size;


public class ProductFragment extends Fragment {


    static Product pro;
    private TextView price, name, desc,categoriaProducto;
    private Button add;
    private OnFragmentInteractionListener mListener;

    static int size;


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


        pro = mListener.getProductoSelected();

        String imagenes [] = {"https://i.imgur.com/S3BBYyc.jpg","https://i.imgur.com/1GNHl4Q.jpg"};

        size = imagenes.length;

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        PhotoGalleryPagerAdapter adapter = new PhotoGalleryPagerAdapter(getContext(), imagenes);

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

                Toast.makeText(getContext(), "Añadido: "+pro.getNombre()+" a la cesta correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        showImages sm = new showImages(viewPager, pro);
        sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                MainActivity.pause = true;

                return false;
            }
        });


        return v;
    }


    public void showData(){

        name.setText(pro.getNombre());
        price.setText(pro.getPrecio()+""+getResources().getText(R.string.badge));
        desc.setText(pro.getDescripcion());
        categoriaProducto.setText(pro.getCategoria());

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

class showImages extends AsyncTask<ViewPager, ArrayList<String>, Product> {
    ViewPager imageSwitcher;
    Product pro;


    public showImages(ViewPager imageSwitcher, Product pro){
        this.imageSwitcher = imageSwitcher;
        this.pro = pro;
    }

    @Override
    protected Product doInBackground(ViewPager... imageSwitchers) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("pausa","nomrmal");
        //comprueba si se ha interactuado con la imagen recientemente y en tal caso pausa la ejecucion del thread
        if (MainActivity.pause) {

            Log.d("pausa","pausado");

            try {
                Thread.sleep(5000);
                MainActivity.pause = false;
                Log.d("pausa","pausado"+MainActivity.pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    @Override
    protected void onPostExecute(Product pro) {
        super.onPostExecute(pro);


        ArrayList<String> asd = null;
        Log.d("pausa","sigue");
        for (int i = 0; i < 1; i++) {

            if (imageSwitcher.getCurrentItem()<ProductFragment.size-1) {

                imageSwitcher.setCurrentItem(imageSwitcher.getCurrentItem() + 1);
            }else{

                imageSwitcher.setCurrentItem(0);


            }
            showImages sm = new showImages(imageSwitcher, pro);
            sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        }
    }
}




