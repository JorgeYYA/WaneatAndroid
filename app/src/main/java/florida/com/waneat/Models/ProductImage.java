package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class ProductImage {
    private Integer id;
    private String image_url;
    private Integer id_product_id;
    private String createdAt;
    private String updatedAt;

    public ProductImage(Integer id, String image_url, Integer id_product_id, String createdAt, String updatedAt) {
        this.id = id;
        this.image_url = image_url;
        this.id_product_id = id_product_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Integer getId_product_id() {
        return id_product_id;
    }

    public void setId_product_id(Integer id_product_id) {
        this.id_product_id = id_product_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
