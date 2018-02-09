package florida.com.waneat.Models;

/**
 * Created by JorgeYYA on 09/02/2018.
 */

public class Restaurant {

    int id;

    String nombre;

    double punctuation;

    public Restaurant(int id, String nombre, double punctuation) {
        this.id = id;
        this.nombre = nombre;
        this.punctuation = punctuation;
    }


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

    public double getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(double punctuation) {
        this.punctuation = punctuation;
    }
}
