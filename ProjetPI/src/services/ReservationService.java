/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnexion;

/**
 *
 * @author Houssem
 */
public class ReservationService {
    Connection cnx;
        Reservation nr = new Reservation();

    public ReservationService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterReservation(Reservation r){
        String sql="INSERT INTO `reservation`(`idRes`, `idUser`, `idHebr`, `idVol`, `valide`, `nbPersonne`, `forfait`, `nbChambre`, `nbSuite`, `dateArr`, `dateDep`, `dateRes`, `deadlineAnnulation`) "
                + "VALUES ('0','"+r.getIdUser()+"','"+r.getIdHebr()+"','"+r.getIdVol()+"','"+r.getValide()+"','"+r.getNbPersonne()+"','"+r.getForfait()+"','"+r.getNbChambre()+"','"+r.getNbSuite()+"','"+r.getDateArr()+"','"+r.getDateDep()+"','"+r.getDateRes()+"','"+r.getDeadlineAnnulation()+"')";
        System.out.println(sql);
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reservation Effectuee avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
   /* public void ajouterReservation2(Reservation p){
        String sql="insert into Reservation(nom,prenom) values(?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
    public Reservation getIdByReservation(Reservation r){   //////  CHECK RESERVATION 
        String query="SELECT * FROM `reservation` WHERE `idUser` = '" + r.getIdUser() + "' AND `idHebr` = '" + r.getIdHebr() + "' AND `idVol` = '" + r.getIdVol() + "' AND `valide` = '" + r.getValide() 
                + "' AND `nbPersonne` = '" + r.getNbPersonne() + "' AND `forfait` = '" + r.getForfait() +"' AND `nbChambre` = '" + r.getNbChambre() 
                + "' AND `nbSuite` = '" + r.getNbSuite() + "' AND `dateArr` = '" + r.getDateArr() + "' AND `dateDep` = '" + r.getDateDep() + "' AND `dateRes` = '" + r.getDateRes() + "' AND"
                + " `deadlineAnnulation` = '" + r.getDeadlineAnnulation() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                nr.setIdRes(rs.getInt("idRes"));
                nr.setIdUser(rs.getInt("idUser"));
                nr.setIdHebr(rs.getInt("idHebr"));
                nr.setIdVol(rs.getInt("idVol"));
                nr.setValide(rs.getInt("valide"));
                nr.setNbPersonne(rs.getInt("nbPersonne"));
                nr.setForfait(rs.getString("forfait"));
                nr.setNbChambre(rs.getInt("nbChambre"));
                nr.setNbSuite(rs.getInt("nbSuite"));
                nr.setDateArr(rs.getDate("dateArr"));
                nr.setDateDep(rs.getDate("dateDep"));
                nr.setDateRes(rs.getDate("dateRes"));
                nr.setDeadlineAnnulation(rs.getDate("deadlineAnnulation"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nr;
    }
    
        public Reservation getReservationById(int i){
            Reservation nr = new Reservation();
            String query="select * from reservation WHERE `idRes` = '" + i +"' ";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                    nr.setIdRes(rs.getInt("idRes"));
                    nr.setIdUser(rs.getInt("idUser"));
                    nr.setIdHebr(rs.getInt("idHebr"));
                    nr.setIdVol(rs.getInt("idVol"));
                    nr.setValide(rs.getInt("valide"));
                    nr.setNbPersonne(rs.getInt("nbPersonne"));
                    nr.setForfait(rs.getString("forfait"));
                    nr.setNbChambre(rs.getInt("nbChambre"));
                    nr.setNbSuite(rs.getInt("nbSuite"));
                    nr.setDateArr(rs.getDate("dateArr"));
                    nr.setDateDep(rs.getDate("dateDep"));
                    nr.setDateRes(rs.getDate("dateRes"));
                    nr.setDeadlineAnnulation(rs.getDate("deadlineAnnulation"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return nr;
        }
    
    public List<Reservation> afficherReservation(){
        List<Reservation> reservations = new ArrayList<>();
        String query="select * from reservation";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Reservation r = new Reservation();
                r.setIdRes(rs.getInt("idRes"));
                r.setIdUser(rs.getInt("idUser"));
                r.setIdHebr(rs.getInt("idHebr"));
                r.setIdVol(rs.getInt("idVol"));
                r.setValide(rs.getInt("valide"));
                r.setNbPersonne(rs.getInt("nbPersonne"));
                r.setForfait(rs.getString("forfait"));
                r.setNbChambre(rs.getInt("nbChambre"));
                r.setNbSuite(rs.getInt("nbSuite"));
                r.setDateArr(rs.getDate("dateArr"));
                r.setDateDep(rs.getDate("dateDep"));
                r.setDateRes(rs.getDate("dateRes"));
                r.setDeadlineAnnulation(rs.getDate("deadlineAnnulation"));
                reservations.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reservations;
        
    }
    
    public void modifierReservation(Reservation or, Reservation nr){
        String query="UPDATE `reservation` SET `idUser` = '" + nr.getIdUser() + "', `idHebr` = '" + nr.getIdHebr() + "', `idVol` = '" + nr.getIdVol() + "', `valide` = '" + nr.getValide() + "', `nbPersonne` = '" + nr.getNbPersonne() + "', `forfait` = '" + nr.getForfait() + "', `nbChambre` = '" + nr.getNbChambre() + "','" +
        "`nbSuite` = '" + nr.getNbSuite() + "', `dateArr` = '" + nr.getDateArr() + "', `dateDep` = '" + nr.getDateDep() + "', `dateRes` = '" + nr.getDateRes() + "', `deadlineAnnulation` = '" + nr.getDeadlineAnnulation() + "' WHERE `reservation`.`idRes` = " + or.getIdRes() + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Réservation Modifiée avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void traiterReservation(Reservation r,int etat){
        String query="UPDATE `reservation` SET `valide` = " + etat + " WHERE `reservation`.`idRes` = " + r.getIdRes() + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Réservation Modifiée avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
///  CANCEL RESERVATION                     - DONE
///  EDIT RESERVATIONS                      - DONE
///  MERGE INTO ONE TABLE                   - DONE
///  TENDANCES HEBERGEMENT                  - DOING
///  BEST CLIENTS IN MONTH OR YEAR          - STILL
