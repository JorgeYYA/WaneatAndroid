package florida.com.waneat.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

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
    public MainActivity activity;

    User u = new User("David", "Florida", "C/Floraida",
             "david@gmail.com", "david123");



    public UserService (Context context){
        this.context = context;
        if(context instanceof MainActivity){
            activity = (MainActivity) context;
        }
        this.prefs = context.getSharedPreferences(sharedName,Context.MODE_PRIVATE);
    }

    /**
     * Método que nos permite comprobar si el usuario esta logueado o no
     */

    public boolean isLoggedIn(){

        if(this.prefs.getBoolean("isSignedIn", false)){
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
        context.getSharedPreferences(sharedName, 0).edit().clear().apply();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.popBackStackImmediate("MY_FRAGMENT", FragmentManager.POP_BACK_STACK_INCLUSIVE);


    }

    /**
     * Método que nos permite iniciar sesión
     */
    public void signIn(String email, String pwd){
        if(email.equalsIgnoreCase(u.getEmail()) && pwd.equalsIgnoreCase(u.getPassword())){
            Toast.makeText(context, "Inicio de sesión completado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
            SharedPreferences.Editor editor = this.prefs.edit();
            editor.putBoolean("isSignedIn", true);
            editor.putString("email", u.getEmail().toString());
            editor.apply();
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
     * Método que nos permite recuperar la información de un usuario a través de su email
     * TODO: Pasar por paremetro el string del email, y recuperar la info de ese usuario
     */
    public User getUserByEmail(){
        return u;
    }



}
