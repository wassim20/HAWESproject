/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
/**
 *
 * @author Houssem
 */
public class Reservation {
    private String forfait; 
    private int idRes,idUser,idHebr,idVol;
    private int valide,nbPersonne,nbChambre,nbSuite;
    private Date dateArr,dateDep,dateRes,deadlineAnnulation; 

    public Reservation() {
    }

    public Reservation(int idRes, int idUser, int idHebr, int idVol, int valide, int nbPersonne, String forfait, int nbChambre, int nbSuite, Date dateArr, Date dateDep, Date dateRes, Date deadlineAnnulation) {
        this.idRes = idRes;
        this.idUser = idUser;
        this.idHebr = idHebr;
        this.idVol = idVol;
        this.valide = valide;
        this.nbPersonne = nbPersonne;
        this.forfait = forfait;
        this.nbChambre = nbChambre;
        this.nbSuite = nbSuite;
        this.dateArr = dateArr;
        this.dateDep = dateDep;
        this.dateRes = dateRes;
        this.deadlineAnnulation = deadlineAnnulation;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdHebr() {
        return idHebr;
    }

    public void setIdHebr(int idHebr) {
        this.idHebr = idHebr;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public String getForfait() {
        return forfait;
    }

    public void setForfait(String forfait) {
        this.forfait = forfait;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public int getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(int nbChambre) {
        this.nbChambre = nbChambre;
    }

    public int getNbSuite() {
        return nbSuite;
    }

    public void setNbSuite(int nbSuite) {
        this.nbSuite = nbSuite;
    }

    public Date getDateArr() {
        return dateArr;
    }

    public void setDateArr(Date dateArr) {
        this.dateArr = dateArr;
    }

    public Date getDateDep() {
        return dateDep;
    }

    public void setDateDep(Date dateDep) {
        this.dateDep = dateDep;
    }

    public Date getDateRes() {
        return dateRes;
    }

    public void setDateRes(Date dateRes) {
        this.dateRes = dateRes;
    }

    public Date getDeadlineAnnulation() {
        return deadlineAnnulation;
    }

    public void setDeadlineAnnulation(Date deadlineAnnulation) {
        this.deadlineAnnulation = deadlineAnnulation;
    }

    
    @Override
    public String toString() {
        return "idReservation =" + idRes + ", idClient =" + idUser + ", idHebergement =" + idHebr + ", idVol =" + idVol
                + ", valide= " + valide + ", nombre des Personne(s) = " + nbPersonne +  ", forfait = " + forfait +  ", nbChambre= " + nbChambre + 
                 ", nbSuite= " + nbSuite + "\ndate Arrivée =" + dateArr + ", date Départ =" + 
                dateDep + ", date Résérvation =" + dateRes + ", deadline Annulation=" + deadlineAnnulation + "}\n\n";
    }
    
    
}
