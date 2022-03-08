/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.ReservationEvent;
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
public class ReservationEventService {
    Connection cnx;

    public ReservationEventService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterReservationEvent(ReservationEvent re){
        String sql="INSERT INTO `reservationevent` (`idRes`, `idEvent`) "
                + "VALUES ('" + re.getIdRes() + "', '" + re.getIdEvent()+ "')";
       // System.out.println(sql);
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            //System.out.println("Reservation d'evenement Effectuee avec succes");
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
    
    public List<ReservationEvent> afficherReservationEvents(){
        List<ReservationEvent> reservationEvents = new ArrayList<>();
        String query="select * from reservationevent";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                ReservationEvent re = new ReservationEvent();
                re.setIdRes(rs.getInt("idRes"));
                re.setIdEvent(rs.getInt("idEvent"));
                reservationEvents.add(re);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reservationEvents;
        
    }
    
    public ReservationEvent afficherReservationEventByReservation(Reservation r){
        ReservationEvent re = new ReservationEvent();
        String query="SELECT * FROM `reservationevent` WHERE `idRes` = '" + r.getIdRes() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                re.setIdRes(rs.getInt("idRes"));
                re.setIdEvent(rs.getInt("idEvent"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return re;
        
    }
    
}
