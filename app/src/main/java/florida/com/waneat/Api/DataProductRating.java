package florida.com.waneat.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergiomoreno on 16/2/18.
 */

public class DataProductRating {
    @SerializedName("id_product_id")
    @Expose
    private int productId;
    @SerializedName("rate")
    @Expose
    private float rate;

    public DataProductRating(int productId, float rate) {
        this.productId = productId;
        this.rate = rate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
