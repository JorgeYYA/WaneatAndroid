package florida.com.waneat.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import florida.com.waneat.Models.User;
import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;
import florida.com.waneat.Services.UserService;


public class UsuarioFragment extends android.support.v4.app.Fragment {


    private EditText perfilUsu, perfilUsername, perfilCorreo, perfilTlf, perfilDir, perfilCiudad, perfilPais, perfilCod;
    private Button buttonGuardar;
    private User user;

    private UserProfileListener mListener;

    public UsuarioFragment() {
    }

    public static UsuarioFragment newInstance() {
        UsuarioFragment fragment = new UsuarioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_usuario, container, false);

        user = mListener.getUser();
        mListener.hideFloatingActionButton();

        this.perfilUsu = (EditText) v.findViewById(R.id.perfilUsu);
        this.perfilUsername = (EditText) v.findViewById(R.id.perfilUsuario);
        this.perfilCorreo = (EditText) v.findViewById(R.id.perfilCorreo);
        this.perfilTlf = (EditText) v.findViewById(R.id.perfilTlf);
        this.perfilDir = (EditText) v.findViewById(R.id.perfilDir);
        this.perfilCiudad = (EditText) v.findViewById(R.id.perfilCiudad);
        this.perfilPais = (EditText) v.findViewById(R.id.perfilPais);
        this.perfilCod = (EditText) v.findViewById(R.id.perfilCod);
        this.buttonGuardar = (Button) v.findViewById(R.id.buttonCambios);


        this.perfilUsu.setText(user.getNombre());
        this.perfilUsername.setText(user.getUsername());
        this.perfilCorreo.setText(user.getEmail());
        this.perfilTlf.setText(user.getContact_phone());
        this.perfilDir.setText(user.getAddress());
        this.perfilCiudad.setText(user.getCity());
        this.perfilPais.setText(user.getState());
        this.perfilCod.setText(user.getPostal_code());


        mListener.changeColorToolbar(true);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setNombre(perfilUsu.getText().toString());
                user.setUsername(perfilUsername.getText().toString());
                user.setEmail(perfilCorreo.getText().toString());
                user.setContact_phone(perfilTlf.getText().toString());
                user.setAddress(perfilDir.getText().toString());
                user.setCity(perfilCiudad.getText().toString());
                user.setState(perfilPais.getText().toString());
                user.setPostal_code(perfilCod.getText().toString());

                perfilUsu.setText(user.getNombre());
                perfilUsername.setText(user.getUsername());
                perfilCorreo.setText(user.getEmail());
                perfilTlf.setText(user.getContact_phone());
                perfilDir.setText(user.getAddress());
                perfilCiudad.setText(user.getCity());
                perfilPais.setText(user.getState());
                perfilCod.setText(user.getPostal_code());

                UserService service = new UserService(getContext());
                //actualizamos el usuario en la api
                service.updateProfile(user);
                //actualizamos el usuario del json
                Preferences.gsonToString(getContext(), user);

                Toast.makeText(v.getContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();
            }
        });

        getActivity().setTitle("Mi perfil");

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof UserProfileListener) {
            mListener = (UserProfileListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface UserProfileListener {
        User getUser();
        void showFloatingActionButton();
        void changeColorToolbar(boolean dark);
        void hideFloatingActionButton();

    }

}
