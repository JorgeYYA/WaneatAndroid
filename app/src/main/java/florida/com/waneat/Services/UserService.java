package florida.com.waneat.Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

import florida.com.waneat.Activities.LoginActivity;
import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Activities.RegisterActivity;
import florida.com.waneat.Api.DataLogin;
import florida.com.waneat.Api.DataStrategy;
import florida.com.waneat.Api.DataWebService;
import florida.com.waneat.Api.RegisterData;
import florida.com.waneat.Models.User;
import florida.com.waneat.Preferences.Preferences;

/**
 * Created by sergiomoreno on 6/2/18.
 */

public class UserService {

    private Context context;
    private String email;
    private String pwd;
    private MainActivity activity;
    private ProgressDialog dialog;
    private DataWebService api = new DataWebService();

    public UserService (Context context){
        this.context = context;
        if(context instanceof MainActivity){
            activity = (MainActivity) context;
        }
    }

    /**
     * Método que nos permite comprobar si el usuario esta logueado o no
     */

    public boolean isLoggedIn(){

        if(Preferences.getBoolean(context,Preferences.SIGNED_IN)){
            //significa que esta logueado
            Toast.makeText(context, "Ya estas logueado", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,MainActivity.class));
            return true;
        }else{
            //no esta logueado
            //Toast.makeText(context, "No estas logueado. Puedes iniciar sesión aquí.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Método que nos permite cerrar sesión del usuario
     */
    public void signOut(){
        Toast.makeText(context, "Cerrando sesión", Toast.LENGTH_SHORT).show();
        Preferences.clean(context);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.popBackStackImmediate("MY_FRAGMENT", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Método que nos permite iniciar sesión
     */
    public void signIn(String email, String pwd){
        try{
            api.login(new DataLogin(email,pwd), new DataStrategy.InteractDispacher() {
                @Override
                public void login(User user) {

                    if(user.getEmail() != null){
                        Toast.makeText(context, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
                        Preferences.gsonToString(context, user);
                        Preferences.setBoolean(context, Preferences.SIGNED_IN, true);
                        Intent i = new Intent(context,MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(i);
                    }else{
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void isError(Throwable t) {
                    Toast.makeText(context, "Fallo en inicio de sesión", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "login: no entra");

                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Método que nos permite registrarnos en la app
     */
    public void registerIn(String nombre, String correo, String contraseña){
        try{
            api.register(new RegisterData(nombre, correo, contraseña), new DataStrategy.InteractDispacherRegister() {
                @Override
                public void register(User user) {
                    Toast.makeText(context, "Registro completado", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, LoginActivity.class));
                }

                @Override
                public void isError(Throwable t) {
                    Toast.makeText(context, "Fallo en el registro", Toast.LENGTH_SHORT).show();
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void updateProfile(User user){
        try{
            api.update(user, new DataStrategy.InteractDispacherUpdate() {
                @Override
                public void update(User user) {
                    Toast.makeText(context, "Se ha actualizado la información correctamente", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void isError(Throwable t) {
                    Toast.makeText(context, "La actualización ha fallado", Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}

