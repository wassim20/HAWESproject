/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author Hassen
 */
public class utilisateurs {
    private int idUser;
    private String cinUser;
    
    
    private String nomUser,
            prenomUser,
            emailUser,
            mdpUser,
            adresseUser,
            telUser,
            role;
   
    public utilisateurs(){
        }

    public utilisateurs(int idUser, String cinUser, String nomUser, String prenomUser, String emailUser, String mdpUser, String adresseUser, String telUser, String role) {
        this.idUser = idUser;
        this.cinUser = cinUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.emailUser = emailUser;
        this.mdpUser = mdpUser;
        this.adresseUser = adresseUser;
        this.telUser = telUser;
        this.role = role;
    }




    
    
    @Override
    public String toString() {
        return "utilisateurs{" + "idUser=" + idUser + ", cinUser=" + cinUser + ", telUser=" + telUser + ", nomUser=" + nomUser + ", emailUser=" + emailUser + ", mdpUser=" + mdpUser + ", adresseUser=" + adresseUser + ", prenomUser=" + prenomUser + '}';
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
    
}
