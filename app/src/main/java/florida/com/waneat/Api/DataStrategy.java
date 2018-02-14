package florida.com.waneat.Api;

import org.json.JSONObject;

import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sergiomoreno on 13/2/18.
 */

public abstract class DataStrategy {

    public abstract void login(DataLogin data,InteractDispacher dispacher);
    public abstract void register(RegisterData data, InteractDispacherRegister dispacher);
    public abstract void update(User user, InteractDispacherUpdate update);
    public abstract void getResta(int id, InteractDispacherGetRestaurants dispacher);


    public interface InteractDispacher {
        void login(User user);
        void isError(Throwable t);
    }

    public interface InteractDispacherRegister {
        void register(User user);
        void isError(Throwable t);
    }

    public interface InteractDispacherUpdate{
        void update(User user);
        void isError(Throwable t);
    }

    public interface InteractDispacherGetRestaurants{
        void getResta(Restaurant restaurant);
        void isError(Throwable t);
    }

    public interface ObjectStrategy {
        /**
         * Método que hace login en Waneat
         * @return User
         */
        @POST("login")
        Call<User> loginUser(@Body DataLogin data);

    }

    public interface ObjectStrategyRegister {
        /**
         * Método que hace registro en Waneat
         * @return User
         */
        @POST("register")
        Call<User> registerUser(@Body RegisterData data);
    }

    public interface ObjectStrategyUpdater {
        /**
         * Método que hace update de un usuario en Waneat
         * @return User
         */
        @POST("updateUser")
        Call<User> updateUser(@Body User userData);
    }

    public interface ObjectStrategyGetRestaurant{
        @GET("restaurant/{id}")
        Call<Restaurant> getRestaurant(@Path("id") int idItem);
    }

}