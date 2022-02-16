
package entities;

import java.util.Date;


public class maison_dhote extends hebergement {
    
    private int nb_chambres;
    private String image;
    private int prix;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    public int getNb_chambres() {
        return nb_chambres;
    }

    public void setNb_chambres(int nb_chambres) {
        this.nb_chambres = nb_chambres;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

  

    public maison_dhote() {
    }

    public maison_dhote(int nb_chambres, String image, int prix, int id_hbg, String city, Date date_ajout, String adress) {
        super(id_hbg, city, date_ajout, adress);
        this.nb_chambres = nb_chambres;
        this.image = image;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return super.toString()+"maison_dhote{" + "nb_chambres=" + nb_chambres + ", image=" + image + ", prix=" + prix + '}';
    }

    
    
    
    
    
}
