/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Hassen
 */
public class utilisateurs {

    public static void setItems(ObservableList<utilisateurs> usersList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int idUser;
    private String cinUser, voiture;
    private String image;

    private Boolean isVerified;
    private String nomUser,
            prenomUser,
            emailUser,
            mdpUser,
            adresseUser,
            telUser,
            role;
  
    public utilisateurs(int idUser, String nomUser, String prenomUser,String cinUser,String telUser,String adresseUser, String emailUser, String role) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.cinUser = cinUser;
        this.telUser = telUser;
        this.adresseUser = adresseUser;
        this.emailUser = emailUser;
        this.role = role;
    }
    public utilisateurs() {
        this.idUser = 0;
    }

    public utilisateurs(int idUser, String cinUser, String nomUser, String prenomUser, String emailUser, String mdpUser, String adresseUser, String telUser, String role, String voiture, String image) {
        this.idUser = idUser;
        this.cinUser = cinUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.emailUser = emailUser;
        this.mdpUser = mdpUser;
        this.adresseUser = adresseUser;
        this.telUser = telUser;
        this.role = role;
        this.voiture = voiture;
        this.image = image;
        this.isVerified = false;

    }


    @Override
    public String toString() {
        return "utilisateurs{" + "idUser=" + idUser + ", cinUser=" + cinUser + ", voiture=" + voiture + ", image=" + image + ", isVerified=" + isVerified + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", emailUser=" + emailUser + ", mdpUser=" + mdpUser + ", adresseUser=" + adresseUser + ", telUser=" + telUser + ", role=" + role + '}';
    }


    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getCinUser() {
        return cinUser;
    }

    public String getTelUser() {
        return telUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getMdpUser() {
        return mdpUser;
    }

    public String getAdresseUser() {
        return adresseUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    public void setTelUser(String telUser) {
        this.telUser = telUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    public void setAdresseUser(String adresseUser) {
        this.adresseUser = adresseUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
