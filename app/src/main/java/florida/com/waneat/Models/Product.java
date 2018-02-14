package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 14/2/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price_product")
    @Expose
    private Double priceProduct;
    @SerializedName("name_product")
    @Expose
    private String nameProduct;
    @SerializedName("description_product")
    @Expose
    private String descriptionProduct;
    @SerializedName("category_product")
    @Expose
    private String categoryProduct;
    @SerializedName("id_restaurant_id")
    @Expose
    private Integer idRestaurantId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("images")
    @Expose
    private List<Image_> images = null;
    @SerializedName("ratings")
    @Expose
    private List<Rating_> ratings = null;

    private int cantidad;

    /**
     * No args constructor for use in serialization
     *
     */
    public Product() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param idRestaurantId
     * @param nameProduct
     * @param categoryProduct
     * @param createdAt
     * @param descriptionProduct
     * @param images
     * @param priceProduct
     * @param ratings
     */
    public Product(Integer id, Double priceProduct, String nameProduct, String descriptionProduct, String categoryProduct, Integer idRestaurantId, String createdAt, String updatedAt, List<Image_> images, List<Rating_> ratings) {
        super();
        this.id = id;
        this.priceProduct = priceProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.categoryProduct = categoryProduct;
        this.idRestaurantId = idRestaurantId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
        this.ratings = ratings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
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

    public List<Image_> getImages() {
        return images;
    }

    public void setImages(List<Image_> images) {
        this.images = images;
    }

    public List<Rating_> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating_> ratings) {
        this.ratings = ratings;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    protected Product(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        priceProduct = in.readByte() == 0x00 ? null : in.readDouble();
        nameProduct = in.readString();
        descriptionProduct = in.readString();
        categoryProduct = in.readString();
        idRestaurantId = in.readByte() == 0x00 ? null : in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
        if (in.readByte() == 0x01) {
            images = new ArrayList<Image_>() {
            };
            in.readList(images, Image_.class.getClassLoader());
        } else {
            images = null;
        }
        if (in.readByte() == 0x01) {
            ratings = new ArrayList<Rating_>();
            in.readList(ratings, Rating_.class.getClassLoader());
        } else {
            ratings = null;
        }
        cantidad = in.readInt();
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
        if (priceProduct == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(priceProduct);
        }
        dest.writeString(nameProduct);
        dest.writeString(descriptionProduct);
        dest.writeString(categoryProduct);
        if (idRestaurantId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(idRestaurantId);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        if (images == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(images);
        }
        if (ratings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ratings);
        }
        dest.writeInt(cantidad);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}