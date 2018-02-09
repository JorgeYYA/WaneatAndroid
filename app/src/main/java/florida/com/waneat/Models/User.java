package florida.com.waneat.Models;

/**
 * Created by 2dam on 06/02/2018.
 */

public class User {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String tlf;
    private String email;
    private String password;

    public User(String nombre, String apellidos, String direccion, String tlf, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.tlf = tlf;
        this.email = email;
        this.password = password;
    }

    public User() {}


    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tlf='" + tlf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
