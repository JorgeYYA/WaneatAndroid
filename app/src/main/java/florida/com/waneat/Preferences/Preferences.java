package florida.com.waneat.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import florida.com.waneat.Models.CreditCard;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Models.User;

public class Preferences {

    public static final String USUARIO_KEY = "usuario";
    public static final String SIGNED_IN  = "isSignedIn";
    public static final String RESTAURANT_KEY  = "restaurant";
    public static final String PREFERRED_CREDIT_CARD = "preferred_card";
    public static final String CREDIT_CARD = "credit_cards";



    public static void setString(Context context, final String key, final String string) {
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shapref.edit();
        editor.putString(key, string);
        editor.apply();
    }


    public static String getString(Context context, final String key) {
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        return shapref.getString(key, null);
    }

    public static boolean getBoolean(Context context, final String key) {
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        return shapref.getBoolean(key, false);
    }

    public static void setBoolean(Context context, final String key, final boolean bolean) {
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shapref.edit();
        editor.putBoolean(key, bolean);
        editor.apply();
    }

    public static int getInt(Context context, final String key) {
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        return shapref.getInt(key, -1);
    }

    public static void setInt(Context context, final String key, final int id) {
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shapref.edit();
        editor.putInt(key, id);
        editor.apply();
    }

    public static void clean(Context context){
        SharedPreferences shapref = PreferenceManager.getDefaultSharedPreferences(context);
        shapref.edit().clear().apply();
    }

    /**
     * Método que nos permite almacenar un objeto usuario en shared preferences
     * @param usuarioPerfil
     */
    public static void gsonToString(Context context, User usuarioPerfil) {
        Gson gson = new Gson();
        setString(context, USUARIO_KEY, gson.toJson(usuarioPerfil));
    }

    public static void restaurantToString(Context context, Restaurant restaurant) {
        Gson gson = new Gson();
        setString(context, RESTAURANT_KEY, gson.toJson(restaurant));
    }

    public static Restaurant gsonToRestaurant(Context context) {
        Gson gson = new Gson();
        Type type = new TypeToken<Restaurant>() {
        }.getType();
        return gson.fromJson(Preferences.getString(context, RESTAURANT_KEY), type);
    }

    public static User gsonToUser(Context context) {
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {
        }.getType();
        return gson.fromJson(Preferences.getString(context, USUARIO_KEY), type);
    }

    public static ArrayList<CreditCard> gsonToCreditCard(Context context) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<CreditCard>>() {
        }.getType();
        return gson.fromJson(Preferences.getString(context, CREDIT_CARD), type);
    }


    public static void creditCardToString (Context context, final String key, final ArrayList<CreditCard> cards) {
        Gson gson = new Gson();
        setString(context, CREDIT_CARD, gson.toJson(cards));
    }

}