/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Houssem
 */
public class Paiement {
    private int idPmt,idRes;
    private Date datePmt;
    private String methode;
    private double montant;
    private int canceled;
    
    public Paiement() {
    }

    public Paiement(int idPmt, int idRes, Date datePmt, String methode, double montant) {
        this.idPmt = idPmt;
        this.idRes = idRes;
        this.datePmt = datePmt;
        this.methode = methode;
        this.montant = montant;
        this.canceled = 0;
    }

    public int getIdPmt() {
        return idPmt;
    }

    public void setIdPmt(int idPmt) {
        this.idPmt = idPmt;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public Date getDatePmt() {
        return datePmt;
    }

    public void setDatePmt(Date datePmt) {
        this.datePmt = datePmt;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    
    
    @Override
    public String toString() {
        return "Paiement{" + "idPmt=" + idPmt + ", idRes=" + idRes + ", datePmt=" + datePmt + ", methode=" + methode + ", montant=" + montant + ", canceled=" + canceled + '}';
    }
    
    
}
