package florida.com.waneat.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Calendar;

import florida.com.waneat.Controllers.OnSwipeTouchListener;
import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

import static florida.com.waneat.Fragments.ProductFragment.fadeInPred;
import static florida.com.waneat.Fragments.ProductFragment.fadeOutPred;
import static florida.com.waneat.Fragments.ProductFragment.pause;
import static florida.com.waneat.Fragments.ProductFragment.sleeper;


public class ProductFragment extends Fragment {


    //Inicialización de variables
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Integer> imagen = new ArrayList<Integer>();


    private static ImageSwitcher imageSwitcher;

    static ProgressBar progres;

    static Product pro;

    static Animation fadeInPred;
    static Animation fadeOutPred;

    static int sleeper = 5000;

    static boolean pause = false;

    static int position = 0;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView price, name, desc,categoriaProducto;

    Button add;

    private OnFragmentInteractionListener mListener;


    public ProductFragment() {

    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_product, container, false);

        price = (TextView) v.findViewById(R.id.p_price);
        name = (TextView) v.findViewById(R.id.p_name);
        desc = (TextView) v.findViewById(R.id.p_desc);
        add = (Button) v.findViewById(R.id.p_add);
        progres = (ProgressBar) v.findViewById(R.id.image_progress);
        categoriaProducto = (TextView) v.findViewById(R.id.categoriaProducto);


        this.imagen.add(R.drawable.plato1);
        this.imagen.add(R.drawable.plato2);


        pro = mListener.getProductoSelected();


        //Crea el ImageSwitcher
        imageSwitcher = (ImageSwitcher) v.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(getActivity());
            }
        });

        //Establece las animaciones predeterminadas
        fadeInPred = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
        fadeOutPred = AnimationUtils.loadAnimation(getActivity(), R.anim.rigth_to_left);


        //Muestra en pantalla los datos del producto recibido
        showData();


        // Establece la animación de transición entre fotos
        setAnim();

        getActivity().setTitle(name.getText().toString());

        //Pone la primera imagen
        try{
            imageSwitcher.setImageResource(pro.getImagen().get(0));
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
        progres.setProgress(1);


       //En caso de haber más de una imagen muestra al usuario un progressbar que indica en que imagen se está mostrando
        showBar();


        //Establece el máximo del progressBar
        progres.setMax(pro.getImagen().size());


        //Establece que al pulsar sobre la imagen cambie a la siguiente
        slideController();


        //Inicia el thread que controlla el loop de imagenes automático
        showImages sm= new showImages(imageSwitcher, progres, pro);
        sm.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR);


        //Listener de "Añadir a la cesta"
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addToCart(pro);

                Toast.makeText(getContext(), "Añadido: "+pro.getNombre()+" a la cesta correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void nextImage(){

        //Un bucle simple para recorrer todas las imágenes
        position++;

        if (position == pro.getImagen().size()) {
            position = 0;
        }
        setProgress();

        imageSwitcher.setImageResource(pro.getImagen().get(position));
    }

    public void prevImage(){

        //Un bucle simple para recorrer todas las imágenes
        if (position == 0) {
            position = pro.getImagen().size();

        }
        position--;
        imageSwitcher.setImageResource(pro.getImagen().get(position));
        setProgress();
    }

    public void showData(){

        name.setText(pro.getNombre());
        price.setText(pro.getPrecio()+""+getResources().getText(R.string.badge));
        desc.setText(pro.getDescripcion());
        categoriaProducto.setText(pro.getCategoria());

    }

    public void setAnim(){

        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.rigth_to_left);
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);

    }

    public void showBar(){

        if(pro.getImagen().size() > 1){

            progres.setVisibility(View.VISIBLE);

        }

    }

    public static void setProgress(){

        progres.setProgress(ProductFragment.position+1);


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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        Product getProductoSelected();
        void addToCart(Product prod);
    }
}

class showImages extends AsyncTask<ImageSwitcher, ProgressBar, Product> {
    ImageSwitcher imageSwitcher;

    ProgressBar progress;

    Product pro;

    public showImages(ImageSwitcher imageSwitcher, ProgressBar progress, Product pro) {
        this.imageSwitcher = imageSwitcher;
        this.progress = progress;
        this.pro = pro;
    }

    @Override
    protected Product doInBackground(ImageSwitcher... imageSwitchers) {

        try {
            Thread.sleep(sleeper);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //comprueba si se ha interactuado con la imagen recientemente y en tal caso pausa la ejecucion del thread
        if (pause) {

            try {
                Thread.sleep(sleeper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pause = false;
        }

        ProductFragment.position++;

        if (ProductFragment.position == pro.getImagen().size()) {
            ProductFragment.position = 0;

        }

        return null;
    }


    @Override
    protected void onPostExecute(Product product) {
        super.onPostExecute(product);
        for (int i = 0; i < 1; i++) {

            imageSwitcher.setInAnimation(fadeInPred);
            imageSwitcher.setOutAnimation(fadeOutPred);
            showImages sm = new showImages(imageSwitcher, progress, pro);
            sm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            imageSwitcher.setImageResource(pro.getImagen().get(ProductFragment.position));
            ProductFragment.setProgress();
        }
    }
}






