package florida.com.waneat.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JorgeYYA on 09/02/2018.
 */

public class Restaurant {
    private Integer id;
    private String name_restaurant;
    private String address_restaurant;
    private String city_restaurant;
    private String postalcode_restaurant;
    private String country_restaurant;
    private String state_restaurant;
    private String description;
    private String email_restaurant;
    private String specialty;
    private String restaurant_url;
    private Integer id_user_id;
    private String created_at;
    private String updated_at;
    private List<RestaurantImage> images = null;
    private List<RestaurantRatings> ratings = null;
    private List<Product> products = null;

    public Restaurant(Integer id, String name_restaurant, String address_restaurant, String city_restaurant, String postalcode_restaurant, String country_restaurant, String state_restaurant, String description, String email_restaurant, String specialty, String restaurant_url, Integer id_user_id, String created_at, String updated_at, List<RestaurantImage> images, List<RestaurantRatings> ratings, List<Product> products) {
        this.id = id;
        this.name_restaurant = name_restaurant;
        this.address_restaurant = address_restaurant;
        this.city_restaurant = city_restaurant;
        this.postalcode_restaurant = postalcode_restaurant;
        this.country_restaurant = country_restaurant;
        this.state_restaurant = state_restaurant;
        this.description = description;
        this.email_restaurant = email_restaurant;
        this.specialty = specialty;
        this.restaurant_url = restaurant_url;
        this.id_user_id = id_user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.images = images;
        this.ratings = ratings;
        this.products = products;
    }

    public Restaurant(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_restaurant() {
        return name_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this.name_restaurant = name_restaurant;
    }

    public String getAddress_restaurant() {
        return address_restaurant;
    }

    public void setAddress_restaurant(String address_restaurant) {
        this.address_restaurant = address_restaurant;
    }

    public String getCity_restaurant() {
        return city_restaurant;
    }

    public void setCity_restaurant(String city_restaurant) {
        this.city_restaurant = city_restaurant;
    }

    public String getPostalcode_restaurant() {
        return postalcode_restaurant;
    }

    public void setPostalcode_restaurant(String postalcode_restaurant) {
        this.postalcode_restaurant = postalcode_restaurant;
    }

    public String getCountry_restaurant() {
        return country_restaurant;
    }

    public void setCountry_restaurant(String country_restaurant) {
        this.country_restaurant = country_restaurant;
    }

    public String getState_restaurant() {
        return state_restaurant;
    }

    public void setState_restaurant(String state_restaurant) {
        this.state_restaurant = state_restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail_restaurant() {
        return email_restaurant;
    }

    public void setEmail_restaurant(String email_restaurant) {
        this.email_restaurant = email_restaurant;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRestaurant_url() {
        return restaurant_url;
    }

    public void setRestaurant_url(String restaurant_url) {
        this.restaurant_url = restaurant_url;
    }

    public Integer getId_user_id() {
        return id_user_id;
    }

    public void setId_user_id(Integer id_user_id) {
        this.id_user_id = id_user_id;
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

    public List<RestaurantImage> getImages() {
        return images;
    }

    public void setImages(List<RestaurantImage> images) {
        this.images = images;
    }

    public List<RestaurantRatings> getRatings() {
        return ratings;
    }

    public void setRatings(List<RestaurantRatings> ratings) {
        this.ratings = ratings;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
