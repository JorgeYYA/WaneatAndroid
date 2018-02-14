package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 12/2/18.
 */

public class RestaurantRatings {

    private Integer id;
    private float rate;
    private Integer id_restaurant_id;
    private Integer id_user_id;
    private String created_at;
    private String updated_at;

    public RestaurantRatings(Integer id, float rate, Integer id_restaurant_id, Integer id_user_id, String created_at, String updated_at ) {
        this.id = id;
        this.rate = rate;
        this.id_restaurant_id = id_restaurant_id;
        this.id_user_id = id_user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user_id() {
        return id_user_id;
    }

    public void setId_user_id(Integer id_user_id) {
        this.id_user_id = id_user_id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Integer getId_restaurant_id() {
        return id_restaurant_id;
    }

    public void setId_restaurant_id(Integer id_restaurant_id) {
        this.id_restaurant_id = id_restaurant_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
