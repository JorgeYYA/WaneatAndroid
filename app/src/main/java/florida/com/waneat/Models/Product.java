package florida.com.waneat.Models;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

import java.util.ArrayList;

/**
 * Created by sergiomoreno on 5/2/18.
 */

public class Product {

    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private ArrayList<Integer> imagen;
    private Drawable ImagenDrawable;
    private String categoria;
    private String comentariosAdicionales;
    private int cantidad;

    public Product(int id, String nombre, String descripcion, double precio, ArrayList<Integer> imagen, String comentariosAdicionales, String categoria, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.comentariosAdicionales = comentariosAdicionales;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    /*public Drawable getImagenDrawable() {
        return ImagenDrawable;
    }*/

    /*public void setImagenDrawable(Drawable imagenDrawable) {
        ImagenDrawable = imagenDrawable;
    }*/

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

    public ArrayList<Integer> getImagen() {
        return imagen;
    }

    public void setImagen(ArrayList<Integer> imagen) {
        this.imagen = imagen;
    }

    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
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
}
