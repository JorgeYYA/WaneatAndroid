package florida.com.waneat.Fragments;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class ProductFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Integer> imagen;

    private static ImageSwitcher imageSwitcher;

    static ProgressBar progres;

    static Product pro;

    static Animation fadeInPred;
    static Animation fadeOutPred;


    static int position = 0;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView price, name, desc,categoriaProducto;

    Button add;

    private OnFragmentInteractionListener mListener;


    public ProductFragment() {
        // Required empty public constructor
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

        imagen = new ArrayList<>();
        imagen.add(R.drawable.plato1);
        imagen.add(R.drawable.plato2);

        pro = new Product(1,"Lorem ipsum dolor sit amet","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",9,imagen," ","Sushi",0);

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
        name.setText(pro.getNombre());
        price.setText(pro.getPrecio()+""+getResources().getText(R.string.badge));
        desc.setText(pro.getDescripcion());
        categoriaProducto.setText(pro.getCategoria());


        // Establece la animación de transición entre fotos
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.rigth_to_left);
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
       imageSwitcher.setInAnimation(fadeIn);
       imageSwitcher.setOutAnimation(fadeOut);


        //Pone la primera imagen
        imageSwitcher.setImageResource(pro.getImagen().get(0));
        progres.setProgress(1);


       //En caso de haber más de una imagen muestra al usuario instrucciones de como alternar entre ellas
        if(pro.getImagen().size() > 1){

            //note.setText("Slide left and right at the image for view more");
            progres.setVisibility(View.VISIBLE);

        }

        //Establece el máximo del progressBar
        progres.setMax(pro.getImagen().size());



        //Establece que al pulsar sobre la imagen cambie a la siguiente

        imageSwitcher.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                //Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                //Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
                Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.letf_to_right);
                Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_out);
                imageSwitcher.setInAnimation(fadeIn);
                imageSwitcher.setOutAnimation(fadeOut);
                prevImage();
            }
            public void onSwipeLeft() {
                //Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
                Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
                Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.rigth_to_left);
                imageSwitcher.setInAnimation(fadeIn);
                imageSwitcher.setOutAnimation(fadeOut);
                nextImage();
            }
            public void onSwipeBottom() {
                //Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        //timer();
        //ASD
        showImages sm= new showImages(imageSwitcher, progres, pro);
        sm.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR);

        /*if (isAdded()) {
            getActivity().runOnUiThread(new Thread() {
                @Override
                public void run() {

                    for (int i = 0; i < 5; i++) {

                        position++;

                        if (position == pro.getImagen().size()) {
                            position = 0;
                            //progres.setProgress(position);
                        }
                        progres.setProgress(position + 1);

                        imageSwitcher.setImageResource(pro.getImagen().get(position));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }*/




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Mi polla", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }


    public static void nextImage(){
        //Un bucle simple para recorrer todas las imágenes

        position++;

        if (position == pro.getImagen().size()) {
            position = 0;
            //progres.setProgress(position);
        }
        setProgress();

        imageSwitcher.setImageResource(pro.getImagen().get(position));
        //Toast.makeText(getActivity(), position+"next", Toast.LENGTH_SHORT).show();

    }

    public void prevImage(){

        //Un bucle simple para recorrer todas las imágenes
        //progres.setProgress(position);

        if (position == 0) {
            position = pro.getImagen().size();
            //progres.setProgress(position);

        }else{

           // progres.setProgress(position+1);
        }
        position--;
        imageSwitcher.setImageResource(pro.getImagen().get(position));
        //Toast.makeText(getActivity(), position+"prev", Toast.LENGTH_SHORT).show();
        setProgress();





    }


    public static void setProgress(){

        progres.setProgress(ProductFragment.position+1);//revisar



    }

    public void timer(){

        //Cosas Joder Cosas
        boolean bo = true;

        int segun = 0;

        int segundos = Calendar.getInstance().getTime().getSeconds();
        int segundos2 = Calendar.getInstance().getTime().getSeconds()+5;

        Calendar.getInstance().getTime();
        Toast.makeText(getActivity(), segundos+"", Toast.LENGTH_SHORT).show();

        do {
            segun ++;
            segundos = Calendar.getInstance().getTime().getSeconds();
            Toast.makeText(getActivity(), segundos+"", Toast.LENGTH_SHORT).show();
            price.setText(segun+"");

            if (segundos == 0){

                nextImage();
                segundos = Calendar.getInstance().getTime().getSeconds();
                segundos2 = Calendar.getInstance().getTime().getSeconds()+5;


            }else{



            }

            if (segundos == segundos2){

                nextImage();
                segundos = Calendar.getInstance().getTime().getSeconds();
                segundos2 = Calendar.getInstance().getTime().getSeconds()+5;


            }else{



            }



        } while (bo);


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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ProductFragment.position++;

        if (ProductFragment.position == pro.getImagen().size()) {
            ProductFragment.position = 0;
            //progres.setProgress(position);
        }




        return null;
    }



    @Override
    protected void onPostExecute(Product product) {
        super.onPostExecute(product);
        for (int i = 0; i < 1; i++) {


            

            imageSwitcher.setInAnimation(fadeInPred);
            imageSwitcher.setOutAnimation(fadeOutPred);
            showImages sm= new showImages(imageSwitcher, progress, pro);
            sm.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR);
            imageSwitcher.setImageResource(pro.getImagen().get(ProductFragment.position));
            ProductFragment.setProgress();

        }



    }
}
