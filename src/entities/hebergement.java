
package entities;
import java.util.*;



public class hebergement {
    private int id_hbg;
    private String city;
    private Date date_ajout;
    private String adress;

    public int getId_hbg() {
        return id_hbg;
    }

    public void setId_hbg(int id_hbg) {
        this.id_hbg = id_hbg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "hebergement{" + "id_hbg=" + id_hbg + ", city=" + city + ", date_ajout=" + date_ajout + ", adress=" + adress + '}';
    }

    public hebergement() {
    }

    public hebergement(int id_hbg, String city, Date date_ajout, String adress) {
        this.id_hbg = id_hbg;
        this.city = city;
        this.date_ajout = date_ajout;
        this.adress = adress;
    }
    
    
    
    
}
