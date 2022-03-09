/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Paiement;
import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
    @author Houssem 
 *
 */
public class PaiementService {
    Connection cnx;

    public PaiementService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterPaiement(Paiement pmt){
        String sql="INSERT INTO `paiement` (`idPmt`, `idRes`, `datePmt`, `methode`, `montant`, `canceled`)"
                + " VALUES ('" + pmt.getIdPmt() + "', '" + pmt.getIdRes() + "', current_timestamp(), '" + pmt.getMethode() + "', '" + pmt.getMontant() + "', '" + pmt.getCanceled() + "')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Paiement effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
   /* public void ajouterPaiement2(Paiement pmt){
        String sql="insert into Paiement(nom,prenom) values(?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1, pmt.getNom());
            ste.setString(2, pmt.getPrenom());
            ste.executeUpdate();
            System.out.println("Paiement effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
    public List<Paiement> afficherPaiement(){
        List<Paiement> paiements = new ArrayList<>();
        String query="select * from Paiement";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Paiement pmt = new Paiement();
                pmt.setIdPmt(rs.getInt("idPmt"));
                pmt.setIdRes(rs.getInt("idRes"));
                pmt.setDatePmt(rs.getDate("datePmt"));
                pmt.setMethode(rs.getString("methode"));
                pmt.setMontant(rs.getDouble("montant"));
                pmt.setCanceled(rs.getInt("canceled"));
                paiements.add(pmt);   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return paiements;
    }
    
        public List<Paiement> afficherPaiementA(){
        List<Paiement> paiements = new ArrayList<>();
        String query="select * from Paiement where canceled = 1";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Paiement pmt = new Paiement();
                pmt.setIdPmt(rs.getInt("idPmt"));
                pmt.setIdRes(rs.getInt("idRes"));
                pmt.setDatePmt(rs.getDate("datePmt"));
                pmt.setMethode(rs.getString("methode"));
                pmt.setMontant(rs.getDouble("montant"));
                pmt.setCanceled(rs.getInt("canceled"));
                paiements.add(pmt);   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return paiements;
    }
    
    
    public Paiement getPaiementByReservation(Reservation r){
        Paiement pmt = new Paiement();
        String query="select * from paiement WHERE `idRes` = '" + r.getIdRes() +"' ";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                pmt.setIdPmt(rs.getInt("idPmt"));
                pmt.setIdRes(rs.getInt("idRes"));
                pmt.setDatePmt(rs.getDate("datePmt"));
                pmt.setMethode(rs.getString("methode"));
                pmt.setMontant(rs.getDouble("montant"));
                pmt.setCanceled(rs.getInt("canceled"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pmt;
    }
    
        public Paiement getPaiementById(int i){
            Paiement pmt = new Paiement();
            String query="select * from paiement WHERE `idPmt` = '" + i +"' ";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                    pmt.setIdPmt(rs.getInt("idPmt"));
                    pmt.setIdRes(rs.getInt("idRes"));
                    pmt.setDatePmt(rs.getDate("datePmt"));
                    pmt.setMethode(rs.getString("methode"));
                    pmt.setMontant(rs.getDouble("montant"));
                    pmt.setCanceled(rs.getInt("canceled"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return pmt;
        }
        
    
        public void modifierPaiement(Paiement op, Paiement p){
        String sql="UPDATE `paiement` SET `methode` = '" + p.getMethode() + "', `montant` = '" + p.getMontant() + "' WHERE `paiement`.`idPmt` = '" + op.getIdPmt() + "'";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Paiement Modifié avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
        public void annulerPaiement(Paiement p){
        String sql="UPDATE `paiement` SET `canceled` = '1' WHERE `paiement`.`idPmt` = '" + p.getIdPmt() + "'";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Paiement annulée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public List<Paiement> cherchePaiement(Object o) {
        String query="";
        String ch="";
        int i=0;
        List<Paiement> paiements = new ArrayList<>();
        if(o.getClass()==ch.getClass()){
            ch=(String) o;
            query="SELECT * FROM `paiement` WHERE `methode` LIKE '%" + ch + "%' OR `datePmt` LIKE '%" + ch + "%' ORDER BY `idPmt` ASC";
            //System.out.println("is String");
        }
        if(o instanceof Integer){
            i=(Integer) o;
            query="SELECT * FROM `paiement` WHERE `idPmt` = " + i + " OR `idRes` = " + i + " OR `montant` LIKE '%" + i + "%' "
            + "OR `canceled` = " + i + " OR `datePmt` LIKE '%" + i + "%' ORDER BY `idPmt` ASC";
            //System.out.println("isInteger");
        }
        try {
            //System.out.println(query);
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Paiement pmt = new Paiement();
                pmt.setIdPmt(rs.getInt("idPmt"));
                pmt.setIdRes(rs.getInt("idRes"));
                pmt.setDatePmt(rs.getDate("datePmt"));
                pmt.setMethode(rs.getString("methode"));
                pmt.setMontant(rs.getDouble("montant"));
                pmt.setCanceled(rs.getInt("canceled"));
                paiements.add(pmt);   
            }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return paiements; 
    }
}


///  ADD PAIEMENT                       - DONE
///  CANCEL PAIEMENT                    - DONE
///  EDIT PAIEMENT                      - DONE
///  SHOW BOTH PAIEMENTS                - DONE
///  STATISTICS ABOUT PAIEMENTS         - STILL