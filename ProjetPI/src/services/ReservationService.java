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
                + "VALUES ('0','"+r.getIdUser()+"','"+r.getIdHebr()+"','"+r.getIdVol()+"','"+r.getValide()+"','"+r.getNbPersonne()+"','"+r.getForfait()+"','"+r.getNbChambre()+"','"+r.getNbSuite()+"','"+r.getDateArr()+"','"+r.getDateDep()+"',current_timestamp(),'"+r.getDeadlineAnnulation()+"')";
        //System.out.println(sql);
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
                + "' AND `nbSuite` = '" + r.getNbSuite() + "' AND `dateArr` = '" + r.getDateArr() + "' AND `dateDep` = '" + r.getDateDep() + "' AND"
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
            String query="select * from reservation WHERE `idRes` = " + i +" ";
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
        String query="UPDATE `reservation` SET `idUser` = '" + nr.getIdUser() + "', `idHebr` = '" + nr.getIdHebr() + "', `idVol` = '" + nr.getIdVol() + "', `valide` = '" + nr.getValide() + "', `nbPersonne` = '" + nr.getNbPersonne() + "', `forfait` = '" + nr.getForfait() + "', `nbChambre` = '" + nr.getNbChambre() + "', `nbSuite` = '" + nr.getNbSuite() + "', `dateArr` = '" + nr.getDateArr() + "', `dateDep` = '" + nr.getDateDep() + "', `dateRes` = '" + nr.getDateRes() + "', `deadlineAnnulation` = '" + nr.getDeadlineAnnulation() + "' WHERE `reservation`.`idRes` = " + or.getIdRes() + "";
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

    public List<Reservation> afficherReservationC() {
        List<Reservation> reservations = new ArrayList<>();
        String query="SELECT * FROM `reservation` WHERE `dateDep` >= CURRENT_TIMESTAMP() AND `valide` = 1";
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

    public List<Reservation> afficherReservationA() {
        List<Reservation> reservations = new ArrayList<>();
        String query="select * from reservation where valide = -1";
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

    public List<Reservation> afficherReservationNT() {
        List<Reservation> reservations = new ArrayList<>();
        String query="select * from reservation where valide = 0";
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
    
        public List<Reservation> chercheReservation(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<Reservation> reservations = new ArrayList<>();
            if(o.getClass()==ch.getClass()){
                ch=(String) o;
                query="SELECT * FROM `reservation` WHERE `forfait` LIKE '%" + ch + "%' OR `dateArr` LIKE '%" + ch + "%' OR `dateDep` LIKE '%" + ch + "%' OR `dateRes` LIKE '%" + ch + "%' OR `deadlineAnnulation` LIKE '%" + ch + "%'";
            }
            if(o instanceof Integer){
                i=(Integer) o;
                query="SELECT * FROM `reservation` WHERE `idRes` = " + i + " OR `idUser` = " + i + " OR `idHebr` = " + i + " OR `idVol` = " + i + " OR `valide` = " + i + " OR `nbPersonne` = " + i + " OR `nbChambre` = " + i + " OR `nbSuite` = " + i + " OR"
                        + " `dateArr` LIKE '%" + i + "%' OR `dateDep` LIKE '%" + i + "%' OR `dateRes` LIKE '%" + i + "%' OR `deadlineAnnulation` LIKE '%" + i + "%'";
            }
            try {
                //System.out.println(query);
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
                    //System.out.println(reservations);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return reservations;   
        }

    public List<Reservation> showStatistics(String moyenne, String occurrence, String somme, String groupby, int idHebr, int order) {
        String sql = "SELECT ";
        if (somme.length() != 0 ){
            if(groupby.length() == 0){
                sql += "SUM(" + somme + ") Somme_" + somme +", ";
            }else if(groupby.length() != 0){
                sql += "SUM(" + somme + ") Somme_" + groupby +", ";                
            }
        }
        if (occurrence.length() != 0){
            if (groupby.length() == 0){
                sql += "count(*) Nb_Doccurrence_" + occurrence +", ";
            }else if (groupby.length() != 0){
                sql += "count(*) Nb_Doccurrence_" + occurrence +", ";
            }
        }
        if (moyenne.length() != 0){
            if (groupby.length() == 0){
                sql += "AVG(" + moyenne + ") moyenne_" + moyenne + ", ";
            }else if(groupby.length() !=0){
                sql += "AVG(" + moyenne + ") moyenne_" + groupby + ", "; 
            }
        }
        if (groupby.length() != 0){
            sql += groupby + " FROM `reservation` "; 
            if(idHebr == 0){
                sql += "GROUP BY " + groupby + " ";
            }else if(idHebr != 0){
                sql += "WHERE idHebr = " + idHebr + " GROUP BY " + groupby + " ";
            }
        }else if (groupby.length() == 0){
            sql += "FROM `reservation` ";
            if(idHebr != 0){
                sql += "WHERE idHebr = " + idHebr + " ";
            }
        }
        if (order == 1){
            sql += "ASC";
        }
        if (order == 0){
            sql += "DESC";
        }
        sql += ";";
        try {
            List<Object> ol = new ArrayList<>();
            PreparedStatement ste= cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                System.out.println(ste);
                rs.getFloat(1);
                rs.getFloat(2);
                rs.getFloat(3);
                rs.getInt(1);
                ol.add(rs);
            }
            System.out.println(ol);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public Reservation getReservationById(Reservation reservation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
///  CANCEL RESERVATION                     - DONE
///  EDIT RESERVATIONS                      - DONE
///  MERGE INTO ONE TABLE                   - DONE
///  TENDANCES HEBERGEMENT                  - DOING
///  BEST CLIENTS IN MONTH OR YEAR          - STILL
