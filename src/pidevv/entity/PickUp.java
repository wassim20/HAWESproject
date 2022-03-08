/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidevv.entity;

/**
 *
 * @author DELL
 */
public class PickUp {

    private int id;
    private int idUser;
    private String adresseDepart;
    private String adresseArrivee;
    private String heureDepart;
    private float prix;

    public PickUp() {
    }

    public PickUp(int id, int idUser, String adresseDepart, String adresseArrivee, String heaureDepart, float prix) {
        this.id = id;
        this.idUser = idUser;
        this.adresseDepart = adresseDepart;
        this.adresseArrivee = adresseArrivee;
        this.heureDepart = heaureDepart;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public String getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(String adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heaureDepart) {
        this.heureDepart = heaureDepart;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return this.adresseArrivee +", "+ this.adresseDepart +", "+ this.heureDepart;
    }
}
