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
import florida.com.waneat.R;


public class UsuarioFragment extends android.support.v4.app.Fragment {


    private EditText perfilUsu, perfilApe, perfilCorreo, perfilTlf, perfilDir, perfilCiudad, perfilPais, perfilCod;
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

        this.perfilUsu = (EditText) v.findViewById(R.id.perfilUsu);
        this.perfilApe = (EditText) v.findViewById(R.id.perfilApe);
        this.perfilCorreo = (EditText) v.findViewById(R.id.perfilCorreo);
        this.perfilTlf = (EditText) v.findViewById(R.id.perfilTlf);
        this.perfilDir = (EditText) v.findViewById(R.id.perfilDir);
        this.perfilCiudad = (EditText) v.findViewById(R.id.perfilCiudad);
        this.perfilPais = (EditText) v.findViewById(R.id.perfilPais);
        this.perfilCod = (EditText) v.findViewById(R.id.perfilCod);
        this.buttonGuardar = (Button) v.findViewById(R.id.buttonCambios);


        this.perfilUsu.setText(user.getNombre());
        this.perfilApe.setText(user.getApellidos());
        this.perfilCorreo.setText(user.getEmail());
        this.perfilTlf.setText(user.getTlf());
        this.perfilDir.setText(user.getDireccion());
        this.perfilCiudad.setText(user.getCiudad());
        this.perfilPais.setText(user.getPais());
        this.perfilCod.setText(user.getCodigo_postal());



        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setNombre(perfilUsu.getText().toString());
                user.setApellidos(perfilApe.getText().toString());
                user.setEmail(perfilCorreo.getText().toString());
                user.setTlf(perfilTlf.getText().toString());
                user.setDireccion(perfilDir.getText().toString());
                user.setCiudad(perfilCiudad.getText().toString());
                user.setPais(perfilPais.getText().toString());
                user.setCodigo_postal(perfilCod.getText().toString());

                perfilUsu.setText(user.getNombre());
                perfilApe.setText(user.getApellidos());
                perfilCorreo.setText(user.getEmail());
                perfilTlf.setText(user.getTlf());
                perfilDir.setText(user.getDireccion());
                perfilCiudad.setText(user.getCiudad());
                perfilPais.setText(user.getPais());
                perfilCod.setText(user.getCodigo_postal());

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

    }

}
