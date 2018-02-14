package florida.com.waneat.Models;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

/**
 * Created by sergiomoreno on 5/2/18.
 */

public class Product implements Parcelable {

    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private ArrayList<Integer> imagen;
    private Drawable ImagenDrawable;
    private String categoria;
    private int cantidad;

    public Product(int id, String nombre, String descripcion, double precio, ArrayList<Integer> imagen, String categoria, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public Product(int id,String nombre, double precio) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;

    }

    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Exclude
    public ArrayList<Integer> getImagen() {
        return imagen;
    }

    public void setImagen(ArrayList<Integer> imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        if (in.readByte() == 0x01) {
            imagen = new ArrayList<Integer>();
            in.readList(imagen, Integer.class.getClassLoader());
        } else {
            imagen = null;
        }
        ImagenDrawable = (Drawable) in.readValue(Drawable.class.getClassLoader());
        categoria = in.readString();
        cantidad = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        if (imagen == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(imagen);
        }
        dest.writeValue(ImagenDrawable);
        dest.writeString(categoria);
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