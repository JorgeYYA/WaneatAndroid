package florida.com.waneat.Models;

import java.util.ArrayList;

/**
 * Created by JorgeYYA on 09/02/2018.
 */

public class Restaurant {

    private Integer id;
    private String nombre, direccion, codigoPostal, pais, descripcion, email, especialidad;
    private String urlWeb;
    private ArrayList<String> urlImagenes;
    private ArrayList<RestaurantRatings> ratings;


    public Restaurant(Integer id, String nombre, String direccion, String codigoPostal, String pais, String descripcion, String email, String especialidad, String urlWeb, ArrayList<String> urlImagenes, ArrayList<RestaurantRatings> ratings) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.descripcion = descripcion;
        this.email = email;
        this.especialidad = especialidad;
        this.urlWeb = urlWeb;
        this.urlImagenes = urlImagenes;
        this.ratings = ratings;
    }

    public Restaurant(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public ArrayList<String> getUrlImagenes() {
        return urlImagenes;
    }

    public void setUrlImagenes(ArrayList<String> urlImagenes) {
        this.urlImagenes = urlImagenes;
    }

    public ArrayList<RestaurantRatings> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<RestaurantRatings> ratings) {
        this.ratings = ratings;
    }
}
