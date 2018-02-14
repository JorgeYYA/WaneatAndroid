package florida.com.waneat.Models;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiomoreno on 5/2/18.
 */

public class Product implements Parcelable {

    private Integer id;
    private Double price_product;
    private String name_product;
    private String description_product;
    private String category_product;
    private Integer id_restaurant_id;
    private String created_at;
    private String updated_at;
    private int cantidad;
    private List<ProductImage> images = null;
    private List<ProductRatings> ratings = null;

    public Product(Integer id, Double price_product, String name_product, String description_product, String category_product, Integer id_restaurant_id, String created_at, String updated_at, List<ProductImage> images, List<ProductRatings> ratings) {
        this.id = id;
        this.price_product = price_product;
        this.name_product = name_product;
        this.description_product = description_product;
        this.category_product = category_product;
        this.id_restaurant_id = id_restaurant_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.images = images;
        this.ratings = ratings;
    }

    public Product(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice_product() {
        return price_product;
    }

    public void setPrice_product(Double price_product) {
        this.price_product = price_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getDescription_product() {
        return description_product;
    }

    public void setDescription_product(String description_product) {
        this.description_product = description_product;
    }

    public String getCategory_product() {
        return category_product;
    }

    public void setCategory_product(String category_product) {
        this.category_product = category_product;
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

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<ProductRatings> getRatings() {
        return ratings;
    }

    public void setRatings(List<ProductRatings> ratings) {
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
        price_product = in.readByte() == 0x00 ? null : in.readDouble();
        name_product = in.readString();
        description_product = in.readString();
        category_product = in.readString();
        id_restaurant_id = in.readByte() == 0x00 ? null : in.readInt();
        created_at = in.readString();
        updated_at = in.readString();
        cantidad = in.readInt();
        if (in.readByte() == 0x01) {
            images = new ArrayList<ProductImage>();
            in.readList(images, ProductImage.class.getClassLoader());
        } else {
            images = null;
        }
        if (in.readByte() == 0x01) {
            ratings = new ArrayList<ProductRatings>();
            in.readList(ratings, ProductRatings.class.getClassLoader());
        } else {
            ratings = null;
        }
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
        if (price_product == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(price_product);
        }
        dest.writeString(name_product);
        dest.writeString(description_product);
        dest.writeString(category_product);
        if (id_restaurant_id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id_restaurant_id);
        }
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeInt(cantidad);
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