package florida.com.waneat.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterOrderList;
import florida.com.waneat.Adapters.AdapterProductList;
import florida.com.waneat.Controllers.OnSwipeTouchListener;
import florida.com.waneat.Models.Order;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

import static florida.com.waneat.Fragments.ProductFragment.fadeInPred;
import static florida.com.waneat.Fragments.ProductFragment.fadeOutPred;
import static florida.com.waneat.Fragments.ProductFragment.pause;
import static florida.com.waneat.Fragments.ProductFragment.sleeper;


public class ShowOrder extends Fragment {


    private String mParam1;
    private String mParam2;

    private ArrayList<Integer> imagen;

    Order order;

    ArrayList<Product> products;

    RecyclerView recyclerProd;

    ImageSwitcher imageSwitcher;

    TextView resName,date,totalPrize;


    static ProgressBar progres;


    static Animation fadeInPred;
    static Animation fadeOutPred;

    static int sleeper = 5000;

    static boolean pause = false;

    static int position = 0;



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

        progres = (ProgressBar) v.findViewById(R.id.image_progress);


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


        //Establece la animaciones predeterminadas
        fadeInPred = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
        fadeOutPred = AnimationUtils.loadAnimation(getActivity(), R.anim.rigth_to_left);

        //Listener para gestionar el cambio de imagenes
        slideController();

        //Recoge la primera imagen de cada producto del pedido
        recoverImages();

        //Establece el máximo del progressBar
        progres.setMax(imagen.size());
        progres.setProgress(1);

        //muestra la barra de progreso de ser necesario
        showBar();

        //Muestra por pantalla los datos del pedido
        resName.setText(order.getResName());
        date.setText(order.getDate());
        totalPrize.setText(order.getTotal()+"");

        //Pone la primera imagen
        imageSwitcher.setImageResource(imagen.get(0));

        showImagesOrder sm = new showImagesOrder(imageSwitcher, progres, imagen);
        sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void recoverImages(){

        for(int i = 0;i<order.getProducts().size();i++){
            if(order.getProducts().get(i).getImagen().get(0) != null) {
                imagen.add(order.getProducts().get(i).getImagen().get(0));
            }

        }

    }

    public void slideController(){

        imageSwitcher.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                //Innecesario
            }
            public void onSwipeRight() {

                Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.letf_to_right);
                Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_out);
                imageSwitcher.setInAnimation(fadeIn);
                imageSwitcher.setOutAnimation(fadeOut);
                pause = true;
                prevImage();
            }
            public void onSwipeLeft() {

                Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
                Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.rigth_to_left);
                imageSwitcher.setInAnimation(fadeIn);
                imageSwitcher.setOutAnimation(fadeOut);
                pause = true;
                nextImage();
            }
            public void onSwipeBottom() {
                //Innecesario
            }

        });


    }

    public void showBar(){

        if(imagen.size() > 1){

            progres.setVisibility(View.VISIBLE);

        }

    }

    public void nextImage(){

        //Un bucle simple para recorrer todas las imágenes
        position++;

        if (position == imagen.size()) {
            position = 0;
        }
        setProgress();

        imageSwitcher.setImageResource(imagen.get(position));
    }

    public void prevImage(){

        //Un bucle simple para recorrer todas las imágenes
        if (position == 0) {
            position = imagen.size();

        }
        position--;
        imageSwitcher.setImageResource(imagen.get(position));
        setProgress();
    }

    public static void setProgress(){

        progres.setProgress(position+1);


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

class showImagesOrder extends AsyncTask<ImageSwitcher, ProgressBar, Product> {
    ImageSwitcher imageSwitcher;

    ProgressBar progress;

    ArrayList<Integer> imagen;

    public showImagesOrder(ImageSwitcher imageSwitcher, ProgressBar progress, ArrayList<Integer> imagen){
        this.imageSwitcher = imageSwitcher;
        this.progress = progress;
        this.imagen = imagen;
    }

    @Override
    protected Product doInBackground(ImageSwitcher... imageSwitchers) {

        try {
            Thread.sleep(sleeper);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //comprueba si se ha interactuado con la imagen recientemente y en tal caso pausa la ejecucion del thread
        if (ShowOrder.pause) {

            try {
                Thread.sleep(ShowOrder.sleeper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ShowOrder.pause = false;
        }

        ShowOrder.position++;

        if (ShowOrder.position == imagen.size()) {
            ShowOrder.position = 0;

        }

        return null;
    }


    @Override
    protected void onPostExecute(Product product) {
        super.onPostExecute(product);
        for (int i = 0; i < 1; i++) {

            imageSwitcher.setInAnimation(ShowOrder.fadeInPred);
            imageSwitcher.setOutAnimation(ShowOrder.fadeOutPred);
            showImagesOrder sm = new showImagesOrder(imageSwitcher, progress, imagen);
            sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            imageSwitcher.setImageResource(imagen.get(ShowOrder.position));
            ShowOrder.setProgress();
        }
    }
}
