package florida.com.waneat.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiomoreno on 14/2/18.
 */

public class Restaurant {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_restaurant")
    @Expose
    private String nameRestaurant;
    @SerializedName("address_restaurant")
    @Expose
    private String addressRestaurant;
    @SerializedName("city_restaurant")
    @Expose
    private String cityRestaurant;
    @SerializedName("postalcode_restaurant")
    @Expose
    private String postalcodeRestaurant;
    @SerializedName("country_restaurant")
    @Expose
    private String countryRestaurant;
    @SerializedName("state_restaurant")
    @Expose
    private String stateRestaurant;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email_restaurant")
    @Expose
    private String emailRestaurant;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("restaurant_url")
    @Expose
    private String restaurantUrl;
    @SerializedName("id_user_id")
    @Expose
    private Integer idUserId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("ratings")
    @Expose
    private List<Rating> ratings = null;
    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Restaurant() {
    }

    /**
     *
     * @param stateRestaurant
     * @param countryRestaurant
     * @param emailRestaurant
     * @param addressRestaurant
     * @param cityRestaurant
     * @param postalcodeRestaurant
     * @param updatedAt
     * @param id
     * @param nameRestaurant
     * @param createdAt
     * @param description
     * @param images
     * @param restaurantUrl
     * @param idUserId
     * @param products
     * @param specialty
     * @param ratings
     */
    public Restaurant(Integer id, String nameRestaurant, String addressRestaurant, String cityRestaurant, String postalcodeRestaurant, String countryRestaurant, String stateRestaurant, String description, String emailRestaurant, String specialty, String restaurantUrl, Integer idUserId, String createdAt, String updatedAt, List<Image> images, List<Rating> ratings, ArrayList<Product> products) {
        super();
        this.id = id;
        this.nameRestaurant = nameRestaurant;
        this.addressRestaurant = addressRestaurant;
        this.cityRestaurant = cityRestaurant;
        this.postalcodeRestaurant = postalcodeRestaurant;
        this.countryRestaurant = countryRestaurant;
        this.stateRestaurant = stateRestaurant;
        this.description = description;
        this.emailRestaurant = emailRestaurant;
        this.specialty = specialty;
        this.restaurantUrl = restaurantUrl;
        this.idUserId = idUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
        this.ratings = ratings;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getAddressRestaurant() {
        return addressRestaurant;
    }

    public void setAddressRestaurant(String addressRestaurant) {
        this.addressRestaurant = addressRestaurant;
    }

    public String getCityRestaurant() {
        return cityRestaurant;
    }

    public void setCityRestaurant(String cityRestaurant) {
        this.cityRestaurant = cityRestaurant;
    }

    public String getPostalcodeRestaurant() {
        return postalcodeRestaurant;
    }

    public void setPostalcodeRestaurant(String postalcodeRestaurant) {
        this.postalcodeRestaurant = postalcodeRestaurant;
    }

    public String getCountryRestaurant() {
        return countryRestaurant;
    }

    public void setCountryRestaurant(String countryRestaurant) {
        this.countryRestaurant = countryRestaurant;
    }

    public String getStateRestaurant() {
        return stateRestaurant;
    }

    public void setStateRestaurant(String stateRestaurant) {
        this.stateRestaurant = stateRestaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailRestaurant() {
        return emailRestaurant;
    }

    public void setEmailRestaurant(String emailRestaurant) {
        this.emailRestaurant = emailRestaurant;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRestaurantUrl() {
        return restaurantUrl;
    }

    public void setRestaurantUrl(String restaurantUrl) {
        this.restaurantUrl = restaurantUrl;
    }

    public Integer getIdUserId() {
        return idUserId;
    }

    public void setIdUserId(Integer idUserId) {
        this.idUserId = idUserId;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}