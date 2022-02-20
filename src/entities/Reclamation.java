/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Fayechi
 */
public class Reclamation {
    private int id_rec,id_hbg,id_user;
    private String desc_rec;
    private int  traite;
    private Date dateAjoutrec,dateTraitrec;
    

    public Reclamation() {
    }

    public Reclamation(int id_rec,String reclamation, int traite, Date dateAjoutrec, Date dateTraitrec, int id_hbg, int id_user) {
        this.id_rec = id_rec;
        this.id_hbg = id_hbg;
        this.id_user = id_user;
        this.desc_rec = reclamation;
        this.traite = traite;
        this.dateAjoutrec = dateAjoutrec;
        this.dateTraitrec = dateTraitrec;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public int getId_hbg() {
        return id_hbg;
    }

    public void setId_hbg(int id_hbg) {
        this.id_hbg = id_hbg;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDesc_rec() {
        return desc_rec;
    }

    public void setDesc_rec(String desc_rec) {
        this.desc_rec = desc_rec;
    }

    public int  getTraite() {
        return traite;
    }

    public void setTraite(int traite) {
        this.traite = traite;
    }

    public Date getDateAjoutrec() {
        return dateAjoutrec;
    }

    public void setDateAjoutrec(Date dateAjoutrec) {
        this.dateAjoutrec = dateAjoutrec;
    }

    public Date getDateTraitrec() {
        return dateTraitrec;
    }

    public void setDateTraitrec(Date dateTraitrec) {
        this.dateTraitrec = dateTraitrec;
    }

   
    
    @Override
    public String toString() {
        return "Reclamation{" + "id reclamation=" + id_rec + ", id hebergement=" + id_hbg +", id utilisateur=" + id_user + ", description=" + desc_rec + ", traitée=" + traite + ", Date Ajout=" + dateAjoutrec + ", Date traitement =" + dateTraitrec + '}';
    }
    
    
}
