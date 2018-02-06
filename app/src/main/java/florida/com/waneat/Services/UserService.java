package florida.com.waneat.Services;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sergiomoreno on 6/2/18.
 */

public class UserService {

    private Context context;
    private String email;
    private String pwd;


    public UserService (Context context){
        this.context = context;
        SharedPreferences prefs = context.getSharedPreferences("Settings",Context.MODE_PRIVATE);
    }

    /**
     * Método que nos permite comprobar si el usuario esta logueado o no
     */
    private boolean isLoggedIn(){
        return true;
    }

    /**
     * Método que nos permite cerrar sesión del usuario
     */
    private void signOut(){

    }

    /**
     * Método que nos permite iniciar sesión
     */
    private void signIn(String email, String pwd){

    }

    /**
     * Método que nos permite registrarnos en la app
     */
    private void registerIn(){

    }

    /**
     * Método que nos permite recuperar la información de un usuario
     */
    private void getUserInformation(){

    }



}
