/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import entities.evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Tools.MyConnexion;

/**
 *
 * @author achre
 */
public class EvenementService {
    Connection cnx;

    public EvenementService() {
        cnx=MyConnexion.getInstance().getCnx();
    }

    /**
     *
     * @param e
     * @param cat
     */
    public void ajouterEvenement(evenement e){
     
        
        String sql="INSERT INTO `evenement`(`id_heb`, `nom`, `nb_place` ,`heureDebut`,`heureFin`,`idCat`,`prix`)"
                + " VALUES ('"+e.getId_heb()+"','"+e.getNom()+"','"+e.getNb_place()+"','"+e.getHeureDebut()+"','"+e.getHeureFin()+"','"+e.getIdCat()+"','"+e.getPrix()+"')";
    
        
        
        
      //  String sql2="INSERT INTO `categorieeve`(`nomCat`)"+  " VALUES ('"+cat.getNomCat()+"')";
        try {
           
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Evenement Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
        
        public evenement getEvenementById(int i){
            evenement e = new evenement();
            String query="select * from evenement WHERE `idEvent` = '" + i +"' ";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                    e.setIdEvent(rs.getInt("idEvent"));
                e.setId_heb(rs.getInt("id_heb"));
                e.setNom(rs.getString("nom"));
                e.setNb_place(rs.getInt("nb_place"));
                e.setHeureDebut(rs.getDate("heureDebut"));
                e.setHeureFin(rs.getDate("heureFin"));
                e.setCategorie(rs.getString("idCat"));
                e.setPrix(rs.getInt("prix"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return e;
        }
    
    /**
     *
     * @return
     */
    public List<evenement> afficherEvenement(){
        List<evenement> evenements;
        evenements = new ArrayList<>();
        String query="select * from evenement";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                evenement e = new evenement();
                e.setIdEvent(rs.getInt("idEvent"));
                e.setId_heb(rs.getInt("id_heb"));
                e.setNom(rs.getString("nom"));
                e.setNb_place(rs.getInt("nb_place"));
                e.setHeureDebut(rs.getDate("heureDebut"));
                e.setHeureFin(rs.getDate("heureFin"));
                e.setCategorie(rs.getString("idCat"));
                e.setPrix(rs.getInt("prix"));
                evenements.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return evenements;
        
    }


public void supprimerEvenement(int idEvent) {
 try { 
            String sql = "DELETE FROM evenement WHERE idEvent="+idEvent+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Evenement Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }    

public void modifierEvenement
        (evenement e,int idEvent) {

        try { 
            String sql = "UPDATE evenement SET  id_heb=?, nom=?,nb_place=? ,heureDebut=? ,heureFin=? , idCat=?, prix=? WHERE idEvent=?";
            PreparedStatement ste= cnx.prepareStatement(sql);
            System.out.println(sql);
            System.out.println(idEvent);
  //          ste.setInt(1,e.getId());
            ste.setInt(1,e.getId_heb());
            ste.setString(2,e.getNom());
            ste.setInt(3,e.getNb_place());
            ste.setDate(4, (java.sql.Date) e.getHeureDebut());
            ste.setDate(5, (java.sql.Date) e.getHeureFin());
            ste.setString(6,e.getIdCat());
            ste.setFloat(7,e.getPrix());
            ste.setInt(8,idEvent);
         
            ste.executeUpdate();

            System.out.println("Evenement Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
        
       public List<evenement> chercheEvenement(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<evenement> evenements = new ArrayList<>();
            if(o instanceof Integer){
                i=(Integer) o;
                query="SELECT * FROM evenement WHERE idEvent = " + i + " OR id_heb = " + i + " OR nom = " + i + " OR nb_place = " + i + " OR idCat = " + i + " OR prix = " + i +" OR"
                        + " heureDebut LIKE '%" + i + "%' OR heureFin LIKE '%" + i + "%'";
            }
            try {
                System.out.println(query);
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                    evenement e = new evenement();
                    e.setIdEvent(rs.getInt("idEvent"));
                    e.setId_heb(rs.getInt("id_heb"));
                    e.setNom(rs.getString("nom"));
                    e.setCategorie(rs.getString("idCat"));
                    e.setHeureDebut(rs.getDate("heureDebut"));
                    e.setHeureFin(rs.getDate("heureFin"));
                    e.setPrix(rs.getFloat("prix"));
                    e.setNb_place(rs.getInt("nb_place"));
       
                    evenements.add(e);
                    System.out.println(evenements);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return evenements;   
        }
       
       
       
           public List<evenement> afficherEvenementPart(){
        List<evenement> evenements;
        evenements = new ArrayList<>();
        String query="select idEvent, id_heb, nom, nb_place, heureDebut, heureFin ,idCat, prix from evenement "
                + "participants   where idUser= "+1+"";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                evenement e = new evenement();
                e.setIdEvent(rs.getInt("idEvent"));
                e.setId_heb(rs.getInt("id_heb"));
                e.setNom(rs.getString("nom"));
                e.setNb_place(rs.getInt("nb_place"));
                e.setHeureDebut(rs.getDate("heureDebut"));
                e.setHeureFin(rs.getDate("heureFin"));
                e.setCategorie(rs.getString("idCat"));
                e.setPrix(rs.getInt("prix"));
                evenements.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return evenements;
        
    }
        
}
// SELECT nomCat from categorieeve where id=? ;