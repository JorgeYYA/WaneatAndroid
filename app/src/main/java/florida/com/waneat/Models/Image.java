package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("id_restaurant_id")
    @Expose
    private Integer idRestaurantId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Image() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param idRestaurantId
     * @param imageUrl
     * @param createdAt
     */
    public Image(Integer id, String imageUrl, Integer idRestaurantId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.imageUrl = imageUrl;
        this.idRestaurantId = idRestaurantId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getIdRestaurantId() {
        return idRestaurantId;
    }

    public void setIdRestaurantId(Integer idRestaurantId) {
        this.idRestaurantId = idRestaurantId;
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