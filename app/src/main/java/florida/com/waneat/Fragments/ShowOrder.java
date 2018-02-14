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
import android.widget.TextView;
import android.widget.Toast;
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


    public static boolean pause;

    private ArrayList<String> imagen;
    private Order order;
    private ArrayList<Product> products;
    private RecyclerView recyclerProd;
    private TextView resName,date,totalPrize;

    private OnFragmentInteractionListener mListener;



    static int position;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_order, container, false);


        String imagenes [] = {"https://i.imgur.com/S3BBYyc.jpg","https://i.imgur.com/1GNHl4Q.jpg"};


        imagen = new ArrayList<String>();

        //asd
        size = imagenes.length;



        mListener.hideFloatingActionButton();

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

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        PhotoGalleryPagerAdapter adapter = new PhotoGalleryPagerAdapter(getContext(), imagenes);

        if (viewPager != null) {
            CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator_default);
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);
            adapter.registerDataSetObserver(indicator.getDataSetObserver());

        }



        //Recoge la primera imagen de cada producto del pedido
        //recoverImages();


        //Muestra por pantalla los datos del pedido
        resName.setText(order.getResName());
        date.setText(order.getDate());
        totalPrize.setText(order.getTotal()+"");


        //TODO: Cambiar a order num
        getActivity().setTitle("Pedido Num: 1");

        showImagesOrder sm = new showImagesOrder(viewPager, order);
        sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                ShowOrder.pause = true;

                return false;
            }
        });


        return v;
    }


    public void recoverImages(){

        for(int i = 0;i<order.getProducts().size();i++){
            if(order.getProducts().get(i).getImagen().get(0) != null) {
                //imagen.add(order.getProducts().get(i).getImagen().get(0));

            }

        }

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


class showImagesOrder extends AsyncTask<ViewPager, ArrayList<String>, Order> {
    ViewPager imageSwitcher;
    Order pro;


    public showImagesOrder(ViewPager imageSwitcher, Order pro){
        this.imageSwitcher = imageSwitcher;
        this.pro = pro;
    }

    @Override
    protected Order doInBackground(ViewPager... imageSwitchers) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("pausa","nomrmal");
        //comprueba si se ha interactuado con la imagen recientemente y en tal caso pausa la ejecucion del thread
        if (ShowOrder.pause) {

            Log.d("pausa","pausado");

            try {
                Thread.sleep(5000);
                ShowOrder.pause = false;
                Log.d("pausa","pausado"+ShowOrder.pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    @Override
    protected void onPostExecute(Order pro) {
        super.onPostExecute(pro);


        ArrayList<String> asd = null;

        for (int i = 0; i < 1; i++) {

            if (imageSwitcher.getCurrentItem()<size-1) {

                imageSwitcher.setCurrentItem(imageSwitcher.getCurrentItem() + 1);
            }else{

                imageSwitcher.setCurrentItem(0);


            }
            showImagesOrder sm = new showImagesOrder(imageSwitcher, pro);
            sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}