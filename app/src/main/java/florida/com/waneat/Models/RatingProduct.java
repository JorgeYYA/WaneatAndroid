package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingProduct {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("id_product_id")
    @Expose
    private Integer idProductId;
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
    public RatingProduct() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param rate
     * @param createdAt
     * @param idProductId
     */
    public RatingProduct(Integer id, Double rate, Integer idProductId, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.rate = rate;
        this.idProductId = idProductId;
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

    public Integer getIdProductId() {
        return idProductId;
    }

    public void setIdProductId(Integer idProductId) {
        this.idProductId = idProductId;
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