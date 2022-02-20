
package entities;
import java.util.*;



public class hebergement {
    private int id_hbg;
    private String nom;
    private String city;
    private Date date_ajout;
    private String adress;
     private String nom_hotel;
    private int nb_chambres;
    private int nb_suites;
    private int piscine;
    private String image;
    private int prix;
    

    
    
    

    public int getId_hbg() {
        return id_hbg;
    }

    public void setId_hbg(int id_hbg) {
        this.id_hbg = id_hbg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getNom_hotel() {
        return nom_hotel;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public int getNb_chambres() {
        return nb_chambres;
    }

    public void setNb_chambres(int nb_chambres) {
        this.nb_chambres = nb_chambres;
    }

    public int getNb_suites() {
        return nb_suites;
    }

    public void setNb_suites(int nb_suites) {
        this.nb_suites = nb_suites;
    }

    public int getPiscine() {
        return piscine;
    }

    public void setPiscine(int piscine) {
        this.piscine = piscine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    

    public hebergement() {
    }

    @Override
    public String toString() {
        return "hebergement{" + "id_hbg=" + id_hbg + ", nom=" + nom + ", city=" + city + ", date_ajout=" + date_ajout + ", adress=" + adress + ", nom_hotel=" + nom_hotel + ", nb_chambres=" + nb_chambres + ", nb_suites=" + nb_suites + ", piscine=" + piscine + ", image=" + image + ", prix=" + prix + '}';
    }

    

    public hebergement(int id_hbg, String nom, String city, Date date_ajout, String adress, String nom_hotel, int nb_chambres, int nb_suites, int piscine, String image, int prix) {
        this.id_hbg = id_hbg;
        this.nom = nom;
        this.city = city;
        this.date_ajout = date_ajout;
        this.adress = adress;
        this.nom_hotel = nom_hotel;
        this.nb_chambres = nb_chambres;
        this.nb_suites = nb_suites;
        this.piscine = piscine;
        this.image = image;
        this.prix = prix;
        
    }

    
    
    
    

    
    
    
    
}
