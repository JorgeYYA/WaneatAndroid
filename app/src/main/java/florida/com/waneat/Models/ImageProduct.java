package florida.com.waneat.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class ImageProduct {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
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
        public ImageProduct() {
        }

        /**
         *
         * @param updatedAt
         * @param id
         * @param imageUrl
         * @param createdAt
         * @param idProductId
         */
        public ImageProduct(Integer id, String imageUrl, Integer idProductId, String createdAt, String updatedAt) {
            super();
            this.id = id;
            this.imageUrl = imageUrl;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
