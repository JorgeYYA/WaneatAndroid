package florida.com.waneat.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JorgeYYA on 08/02/2018.
 */

public class Order implements Serializable {

    int id;

    ArrayList<Product> products;

    String date, resName;

    double total;

    public Order(ArrayList<Product> products, String date, String resName, double total) {
        this.products = products;
        this.date = date;
        this.resName = resName;
        this.total = total;
    }

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
}
