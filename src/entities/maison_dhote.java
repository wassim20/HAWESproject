
package entities;

import java.util.Date;


public class maison_dhote extends hebergement {
    
    private int nb_chambres;
    private int prix;

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

    @Override
    public String toString() {
        return "maison_dhote{" + "nb_chambres=" + nb_chambres + ", prix=" + prix + '}';
    }

    public maison_dhote() {
    }

    public maison_dhote(int nb_chambres, int prix, int id_hbg, String city, Date date_ajout, String adress) {
        super(id_hbg, city, date_ajout, adress);
        this.nb_chambres = nb_chambres;
        this.prix = prix;
    }
    
    
    
    
}
