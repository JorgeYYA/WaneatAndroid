package florida.com.waneat.Models;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class ImageProduct implements Parcelable {

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


    protected ImageProduct(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        imageUrl = in.readString();
        idProductId = in.readByte() == 0x00 ? null : in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(imageUrl);
        if (idProductId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(idProductId);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ImageProduct> CREATOR = new Parcelable.Creator<ImageProduct>() {
        @Override
        public ImageProduct createFromParcel(Parcel in) {
            return new ImageProduct(in);
        }

        @Override
        public ImageProduct[] newArray(int size) {
            return new ImageProduct[size];
        }
    };
}