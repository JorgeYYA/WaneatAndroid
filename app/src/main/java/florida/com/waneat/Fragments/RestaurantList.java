package florida.com.waneat.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import florida.com.waneat.Adapters.AdapterLastVisited;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.R;


public class RestaurantList extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recyclerLasts;
    Button scanQr;

    ArrayList<Restaurant> restaurants;

    private OnFragmentInteractionListener mListener;

    public RestaurantList() {
        // Required empty public constructor
    }


    public static RestaurantList newInstance(String param1, String param2) {
        RestaurantList fragment = new RestaurantList();
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
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        recyclerLasts = (RecyclerView) v.findViewById(R.id.recycler_lasts);


        restaurants = new ArrayList<>();



        LinearLayoutManager llm = new LinearLayoutManager(getActivity());


        recyclerLasts.setLayoutManager(llm);

        //Esto es debug
        restaurants.add(new Restaurant(1,"Esto aparentemente",3.5));
        restaurants.add(new Restaurant(2,"No sirve para nada :3",4));


        recyclerLasts.setAdapter(new AdapterLastVisited(restaurants, new AdapterLastVisited.OnItemClickListener() {

            @Override
            public void onItemClick(Restaurant item) {


                Toast.makeText(getActivity(), item.getNombre(), Toast.LENGTH_SHORT).show();




            }

        }));


        if (restaurants.size()==0){

            scanQr.setVisibility(View.VISIBLE);
            recyclerLasts.setVisibility(View.GONE);

        }else{

            scanQr.setVisibility(View.GONE);
            recyclerLasts.setVisibility(View.VISIBLE);

        }



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
