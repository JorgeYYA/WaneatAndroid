package florida.com.waneat.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JorgeYYA on 08/02/2018.
 */

public class Order implements Parcelable {

    int id;

    int userId;

    ArrayList<Product> products;

    String date, resName;

    double total;


    public Order() {}

    public Order(int userId, ArrayList<Product> products, String date, String resName, double total) {
        this.products = products;
        this.date = date;
        this.resName = resName;
        this.total = total;
        this.userId = userId;
    }

    protected Order(Parcel in) {
        id = in.readInt();
        products = in.createTypedArrayList(Product.CREATOR);
        date = in.readString();
        resName = in.readString();
        total = in.readDouble();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeTypedList(products);
        parcel.writeString(date);
        parcel.writeString(resName);
        parcel.writeDouble(total);
    }
}
