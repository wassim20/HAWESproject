/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.sun.javafx.beans.IDProperty;
import Entités.Paiement;
import Entités.Reservation;
import Entités.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tools.MyConnexion;

/**
 *
 * @author Houssem
 */
public class TicketService {
    Connection cnx;

    public TicketService() {
        cnx=MyConnexion.getInstance().getCnx();
    }
    public void ajouterTicket(Ticket t){
        String sql="INSERT INTO `ticket` VALUES ('0','"+t.getIdPmt()+"','"+t.getIdRes()+"','"+t.getDeleted()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Ticket Enregistre");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    public void supprimerTicket(int idTicket){
        String sql="UPDATE `ticket` SET `deleted` = '1' WHERE `ticket`.`idTicket` = '" +idTicket+ "'";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Ticket supprimé avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    public void modifierTicket(int idTicket, Ticket t2){
        String sql="UPDATE `ticket` SET `idPmt` = '" + t2.getIdPmt() + "', `idRes` = '" + t2.getIdRes() + "' WHERE `ticket`.`idTicket` = '" +idTicket+ "'";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Ticket Modifié avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    /* public void ajouterTicket2(Ticket t){
        String sql="insert into Ticket(nom,prenom) values(?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.executeUpdate();
            System.out.println("Ticket Enregistre");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
    public List<Ticket> afficherTicketValable(){
        List<Ticket> tickets = new ArrayList<>();
        String query="select * from ticket where deleted=0";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setIdPmt(rs.getInt("idPmt"));
                t.setIdRes(rs.getInt("idRes"));
                tickets.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(tickets);
        return tickets; 
    }
    
    public List<Ticket> afficherTicketSupprime(){
    List<Ticket> tickets = new ArrayList<>();
    String query="select * from ticket where deleted=1";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setIdPmt(rs.getInt("idPmt"));
                t.setIdRes(rs.getInt("idRes"));
                t.setDeleted(rs.getInt("deleted"));
                tickets.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return tickets; 
    }
    
    public Ticket GetTicketByResAndPai(Paiement p, Reservation r){
    List<Ticket> tickets = new ArrayList<>();
    Ticket t = new Ticket();
    String query="SELECT * FROM `ticket` WHERE `idPmt` = " + p.getIdPmt() + " AND `idRes` = " + r.getIdRes() + "";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                t.setIdTicket(rs.getInt("idTicket"));
                t.setIdPmt(rs.getInt("idPmt"));
                t.setIdRes(rs.getInt("idRes"));
                t.setDeleted(rs.getInt("deleted"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return t; 
    }
    
    public Ticket GetTicketById(int id){
    Ticket ticket = new Ticket();
    String query="SELECT * FROM `ticket` WHERE `idTicket` = " + id + "";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                ticket.setIdTicket(rs.getInt("idTicket"));
                ticket.setIdPmt(rs.getInt("idPmt"));
                ticket.setIdRes(rs.getInt("idRes"));
                ticket.setDeleted(rs.getInt("deleted"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return ticket; 
    }
        
    
}

///  ADD TICKET             - DONE
///  CANCEL TICKET          - DONE
///  EDIT TICKET            - DONE
///  SHOW BOTH TICKETS      - DONE
///     METIER?????