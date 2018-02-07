package florida.com.waneat.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

import static android.view.animation.Animation.ZORDER_BOTTOM;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Integer> imagen;

    private ImageSwitcher imageSwitcher;

    ProgressBar progres;

    Product pro;


    private int position = 0;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView price, name, desc, note;

    Button add;

    private OnFragmentInteractionListener mListener;


    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        note = (TextView) v.findViewById(R.id.text_note);
        add = (Button) v.findViewById(R.id.p_add);
        progres = (ProgressBar) v.findViewById(R.id.image_progress);

        imagen = new ArrayList<>();
        imagen.add(R.drawable.test);
        imagen.add(R.drawable.test2);
        imagen.add(R.drawable.test);

        pro = new Product(1,"Screenshot","El buen Debugging",9,imagen," ","Debug",0);

        //Crea el ImageSwitcher
        imageSwitcher = (ImageSwitcher) v.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(getActivity());
            }
        });



        //Muestra en pantalla los datos del producto recibido
        name.setText(pro.getNombre());
        price.setText(pro.getPrecio()+"");
        desc.setText(pro.getDescripcion());

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

            note.setText("Slide left and right at the image for view more");
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Mi polla", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }


    public void nextImage(){
        //Un bucle simple para recorrer todas las imágenes

        position++;

        if (position == pro.getImagen().size()) {
            position = 0;
            //progres.setProgress(position);
        }
        progres.setProgress(position+1);

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
        progres.setProgress(position+1);





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


