/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author GX15
 */
public class hotel extends hebergement {
   
    private String nom_hotel;
    private int etoile;
    private int nb_chambres;
    private int nb_suites;
    private int piscine;
    private String image;
    private int prix;
    

    public String getNom_hotel() {
        return nom_hotel;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return  super.toString()+"hotel{" + "nom_hotel=" + nom_hotel + ", etoile=" + etoile + ", nb_chambres=" + nb_chambres + ", nb_suites=" + nb_suites + ", piscine=" + piscine + ", image=" + image + ", prix=" + prix + '}';
    }

    

    public hotel() {
    }

    public hotel(String nom_hotel, int etoile, int nb_chambres, int nb_suites, int piscine, String image, int prix, int id_hbg, String city, Date date_ajout, String adress) {
        super(id_hbg, city, date_ajout, adress);
        this.nom_hotel = nom_hotel;
        this.etoile = etoile;
        this.nb_chambres = nb_chambres;
        this.nb_suites = nb_suites;
        this.piscine = piscine;
        this.image = image;
        this.prix = prix;
    }


    
    
    
    
}
