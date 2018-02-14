package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class ProductRatings {
    private Integer id;
    private float rate;
    private Integer id_product_id;
    private String created_at;
    private String updated_at;

    public ProductRatings(Integer id, float rate, Integer id_product_id, String created_at, String updated_at) {
        this.id = id;
        this.rate = rate;
        this.id_product_id = id_product_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Integer getId_product_id() {
        return id_product_id;
    }

    public void setId_product_id(Integer id_product_id) {
        this.id_product_id = id_product_id;
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
