package florida.com.waneat.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergiomoreno on 16/2/18.
 */

public class DataRestaurantRating {

    @SerializedName("id_restaurant_id")
    @Expose
    private Integer restaurantId;
    @SerializedName("id_user_id")
    @Expose
    private Integer userId;
    @SerializedName("rate")
    @Expose
    private float rate;

    public DataRestaurantRating(Integer restaurantId, Integer userId, float rate) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.rate = rate;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}

