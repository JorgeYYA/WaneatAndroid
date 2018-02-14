package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class RestaurantImage {

    private Integer id;
    private String image_url;
    private Integer id_restaurant_id;
    private String created_at;
    private String updated_at;

    public RestaurantImage(Integer id, String image_url, Integer id_restaurant_id, String created_at, String updated_at) {
        this.id = id;
        this.image_url = image_url;
        this.id_restaurant_id = id_restaurant_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
