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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;

import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

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


    private int position;


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

        imagen = new ArrayList<>();
        imagen.add(R.drawable.test);
        imagen.add(R.drawable.test2);

        Product pro = new Product(1,"Screenshot","El buen Debugging",9,imagen," ","Debug",0);

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
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_out);
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_forward_in);
        imageSwitcher.setInAnimation(fadeOut);
        imageSwitcher.setOutAnimation(fadeIn);


        //Pone la primera imagen
        changeImage();

       //En caso de haber más de una imagen muestra al usuario instrucciones de como alternar entre ellas
        if(pro.getImagen().size() > 1){

            note.setText("Tap on the image for view more");

        }



        //Establece que al pulsar sobre la imagen cambie a la siguiente
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeImage();

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


    public void changeImage(){

        //Un bucle simple para recorrer todas las imágenes
        imageSwitcher.setImageResource(imagen.get(position));
        position++;
        if (position == imagen.size()) {
            position = 0;
        }

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

                changeImage();
                segundos = Calendar.getInstance().getTime().getSeconds();
                segundos2 = Calendar.getInstance().getTime().getSeconds()+5;


            }else{



            }

            if (segundos == segundos2){

                changeImage();
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


