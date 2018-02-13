package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 12/2/18.
 */

public class RestaurantRatings {

    private Integer id;
    private double rate;
    private Integer id_restaurant_id;

    public RestaurantRatings(Integer id, double rate, Integer id_restaurant_id) {
        this.id = id;
        this.rate = rate;
        this.id_restaurant_id = id_restaurant_id;
    }

    public RestaurantRatings(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Integer getId_restaurant_id() {
        return id_restaurant_id;
    }

    public void setId_restaurant_id(Integer id_restaurant_id) {
        this.id_restaurant_id = id_restaurant_id;
    }
}
