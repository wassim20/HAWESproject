/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidevv.entity;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Vol {

    private int id;
    private String compagnie;
    private String destination;
    private Date dateDepart;
    private String heureDepart;
    private String heureArrivee;
    private String avion;
    private int places;
    private float prix;

    public Vol() {
    }

    public Vol(int id, String compagnie, String destination, Date dateDepart, String heureDepart, String heureArrivee, String avion, int places, float prix) {
        this.id = id;
        this.compagnie = compagnie;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.avion = avion;
        this.places = places;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getAvion() {
        return avion;
    }

    public void setAvion(String avion) {
        this.avion = avion;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vol = ["+ this.id +", "+ this.compagnie +", "+ this.destination +", "+ this.heureArrivee +", "+ this.heureDepart +", "+ this.dateDepart +", "+ this.prix +"]\n";
    }

}
