/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.*;
import java.sql.Date;
/**
 *
 * @author achre
 */
public class evenement {
    public int idEvent;
    private int id_heb;
    private String nom;
    private int nb_place;
    private Date heureDebut,heureFin;
    private String idCat;
    private float prix;
    
    
    
         public evenement() {
    }

    public evenement(int idEvent, int id_heb, String nom, int nb_place, Date heureDebut, Date heureFin, String idCat, float prix) {
        this.idEvent = idEvent;
        this.id_heb = id_heb;
        this.nom = nom;
        this.nb_place = nb_place;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idCat = idCat;
        this.prix = prix;
    }
         
         public evenement(int id_heb,String nom,int nb_place,Date heureDebut,Date heureFin
         ,String idCat,float prix){
         //    this.id=id;
             this.id_heb=id_heb;
             this.nom=nom;
             this.nb_place=nb_place;
             this.heureDebut=heureDebut;
             this.heureFin=heureFin;
             this.idCat=idCat;
             this.prix=prix;         
         }
         
 /*   public int getId() {
        return id;
    }*/

    public int getId_heb() {
        return id_heb;
    }    

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNom() {
        return nom;
    }

    public int getNb_place() {
        return nb_place;
    }
    
    /**
     *
     * @return
     */
    public Date getHeureDebut() {
        return heureDebut;
    }
    
    /**
     *
     * @return
     */
    public Date getHeureFin() {
        return heureFin;
    }
    
    public String getIdCat() {
        return idCat;
    }   
 
    public float getPrix() {
        return prix;
    }    
    
   /* public void setId(int id) {
        this.id = id;
    }*/
    
    public void setId_heb(int id_heb) {
        this.id_heb = id_heb;
    }


    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    /**
     *
     * @param heureDebut
     */
    public void setHeureDebut(Date heureDebut) {
        this.heureDebut=heureDebut;
    }

    /**
     *
     * @param heureFin
     */
    public void setHeureFin(Date heureFin) {
        this.heureFin=heureFin;    
    }

    public void setCategorie(String idCat) {
        this.idCat=idCat;
    }

    public void setPrix(float prix) {
        this.prix=prix;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    @Override
    public String toString() {
        return "evenement{" + "idEvent=" + idEvent + ", id_heb=" + id_heb + ", nom=" + nom + ", nb_place="
                + nb_place + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", idCat=" + idCat +
                ", prix=" + prix + '}';
    }
    
    
    
    
   
    
}
