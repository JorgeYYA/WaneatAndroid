package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("id_restaurant_id")
    @Expose
    private Integer idRestaurantId;
    @SerializedName("id_user_id")
    @Expose
    private Integer idUserId;
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
    public Rating() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param idRestaurantId
     * @param rate
     * @param createdAt
     * @param idUserId
     */
    public Rating(Integer id, Double rate, Integer idRestaurantId, Integer idUserId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.rate = rate;
        this.idRestaurantId = idRestaurantId;
        this.idUserId = idUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getIdRestaurantId() {
        return idRestaurantId;
    }

    public void setIdRestaurantId(Integer idRestaurantId) {
        this.idRestaurantId = idRestaurantId;
    }

    public Integer getIdUserId() {
        return idUserId;
    }

    public void setIdUserId(Integer idUserId) {
        this.idUserId = idUserId;
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