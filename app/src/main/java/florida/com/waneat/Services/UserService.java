package florida.com.waneat.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by sergiomoreno on 6/2/18.
 */

public class UserService {

    private Context context;
    private String email;
    private String pwd;
    private SharedPreferences prefs;

    public UserService (Context context){
        this.context = context;
        this.prefs = context.getSharedPreferences("Settings",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = this.prefs.edit();
    }

    /**
     * Método que nos permite comprobar si el usuario esta logueado o no
     */
    private boolean isLoggedIn(){

        if(this.prefs.getBoolean("isSignedIn", false)){
            //significa que esta logueado

        }else{
            //no esta logueado
            Toast.makeText(context, "No estas logueado", Toast.LENGTH_SHORT).show();
        }
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
