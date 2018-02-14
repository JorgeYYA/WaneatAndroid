package florida.com.waneat.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import florida.com.waneat.Activities.MainActivity;
import florida.com.waneat.Api.DataStrategy;
import florida.com.waneat.Api.DataWebService;
import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Preferences.Preferences;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class RestaurantService {

    private Context context;
    private MainActivity activity;
    private DataWebService api = new DataWebService();


    public RestaurantService(Context context) {
        this.context = context;
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }

        /**
         * Método para coger toda la información de un restaurante
         */
        public void getRestaurant(int id){
            Log.d("TAG", "getRestaurant: "+id);
            api.getResta(id, new DataStrategy.InteractDispacherGetRestaurants() {
                @Override
                public void getResta(Restaurant restaurant) {
                    Toast.makeText(context, "Información del restaurante recuperada correctamente", Toast.LENGTH_SHORT).show();
                    Log.d("getResta ", "getResta: "+restaurant.getName_restaurant());
                    Preferences.restaurantToString(context, restaurant);
                }

                @Override
                public void isError(Throwable t) {
                    Toast.makeText(context, "Ha fallado la conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }

}
