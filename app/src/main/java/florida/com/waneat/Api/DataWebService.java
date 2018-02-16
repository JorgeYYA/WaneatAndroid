package florida.com.waneat.Api;

import android.util.Log;

import org.json.JSONObject;

import florida.com.waneat.Models.Restaurant;
import florida.com.waneat.Models.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sergiomoreno on 13/2/18.
 */

public class DataWebService extends DataStrategy  {

    Retrofit retrofit;

    public DataWebService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://waneat.es/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void login(DataLogin json,final InteractDispacher dispacher) {
        ObjectStrategy objectStrategy = retrofit.create(ObjectStrategy.class);
        Call<User> call = objectStrategy.loginUser(json);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dispacher.login(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dispacher.isError(t);
            }
        });
    }

    @Override
    public void register(RegisterData data, final InteractDispacherRegister dispacher) {
        ObjectStrategyRegister objectStrategyRegister = retrofit.create(ObjectStrategyRegister.class);
        Call<User> call = objectStrategyRegister.registerUser(data);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dispacher.register(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dispacher.isError(t);
            }
        });
    }

    @Override
    public void update(User user, final InteractDispacherUpdate update) {
        ObjectStrategyUpdater updater = retrofit.create(ObjectStrategyUpdater.class);
        Call<User> call = updater.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                update.update(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                update.isError(t);
            }
        });
    }

    @Override
    public void getRestaurant(int id, final InteractDispacherGetRestaurants dispacher) {
        ObjectStrategyGetRestaurant getterRestaurant = retrofit.create(ObjectStrategyGetRestaurant.class);
        Call<Restaurant> call = getterRestaurant.getRestaurant(id);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                dispacher.getRestaurant(response.body());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                dispacher.isError(t);
            }
        });
    }

    @Override
    public void insertRating(DataRestaurantRating data, InteractDispacherRestaurantRating dispacher) {

    }

    @Override
    public void insertProductRating(DataProductRating data, InteractDispacherRestaurantRating dispacher) {

    }
}

