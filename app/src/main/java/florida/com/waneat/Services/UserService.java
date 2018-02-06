package florida.com.waneat.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import florida.com.waneat.Activities.LoginActivity;
import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Models.User;

/**
 * Created by sergiomoreno on 6/2/18.
 */

public class UserService {

    private Context context;
    private String email;
    private String pwd;
    private SharedPreferences prefs;
    final static String sharedName = "Settings";

    User u = new User("David", "Florida", "C/Floraida",
            "123456789", "david@gmail.com", "david123");

    public UserService (Context context){
        this.context = context;
        this.prefs = context.getSharedPreferences(sharedName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = this.prefs.edit();
    }

    /**
     * Método que nos permite comprobar si el usuario esta logueado o no
     */
    public boolean isLoggedIn(){

        if(this.prefs.getBoolean("isSignedIn", false)){
            //significa que esta logueado
            Toast.makeText(context, "Ya estas logueado", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,MainActivity.class));

        }else{
            //no esta logueado
            Toast.makeText(context, "No estas logueado", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,LoginActivity.class));
        }
        return true;
    }

    /**
     * Método que nos permite cerrar sesión del usuario
     */
    public void signOut(){
        Toast.makeText(context, "Cerrando sesión", Toast.LENGTH_SHORT).show();
        context.getSharedPreferences(sharedName, 0).edit().clear().commit();
    }

    /**
     * Método que nos permite iniciar sesión
     */
    public void signIn(String email, String pwd){
        if(email.equalsIgnoreCase(u.getEmail()) && pwd.equalsIgnoreCase(u.getPassword())){
            Toast.makeText(context, "Inicio de sesión completado", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,MainActivity.class));

        }else{
            Toast.makeText(context, "Fallo en inicio de sesión", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que nos permite registrarnos en la app
     */
    public void registerIn(){

    }

    /**
     * Método que nos permite recuperar la información de un usuario
     */
    public void getUserInformation(){

    }



}
