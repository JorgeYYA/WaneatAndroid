package florida.com.waneat.Models;

/**
 * Created by 2dam on 06/02/2018.
 */

public class User {
    
    private Integer id;
    private String name;
    private String username;
    private String address;
    private String email;
    private String city;
    private String state;
    private String postal_code;
    private String nif;
    private String contact_phone;

    public User(Integer id, String name, String username, String address, String email, String city, String state, String postal_code, String nif, String contact_phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.email = email;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.nif = nif;
        this.contact_phone = contact_phone;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
