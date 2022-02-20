/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Fayechi
 */
public class ReclamationService {
    Connection cnx;

    public ReclamationService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterReclamation(Reclamation r){
        String sql="INSERT INTO `reclamation`(`id_rec`, `desc_rec`, `traite` ,`dateAjoutrec`, `dateTraitrec`, `id_hbg`, `id_user`) VALUES ('"+r.getId_rec()+"','"+r.getDesc_rec()+"','"+r.getTraite()+"','"+r.getDateAjoutrec()+"','"+r.getDateTraitrec()+"','"+r.getId_hbg()+"','"+r.getId_user()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reclamation Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    ///////////
public void modifierReclamation(Reclamation r,int id, String desc_rec,int Trait, Date dateAjoutrec , Date dateTraitrec, int Id_hbg, int Id_user) {
String sql="UPDATE `reclamation` SET `desc_rec` = '" + desc_rec + "', `traite` = '" + Trait + "', `dateAjoutrec` = '" + dateAjoutrec + "', `dateTraitrec` = '" + dateTraitrec + "', `id_hbg` = '" + Id_hbg + "', `id_user` = '" + Id_user + "' WHERE `reclamation`.`id_rec` = '"+id+"'";        
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reclamation Modifieé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }







/*
      
      public void modifierReclamation(Reclamation r,int id, String des_rec,int Trait, Date dateAjoutrec , Date dateTraitrec, int Id_hbg, int Id_user) {

        try { 
            String sql = "UPDATE reclamation SET desc_rec=?,traite=? ,dateAjoutrec=? ,dateTraitrec=? , id_hbg=?, id_user=?,etat=? WHERE id=?";
            PreparedStatement ste= cnx.prepareStatement(sql);
            
           
                ste.setString(1, r.getDesc_rec());
            ste.setInt(2, r.getTraite());
            ste.setDate(1,r.getDateAjoutrec());
            ste.setDate(2,r.getDateTraitrec());
            ste.setInt(3, r.getId_hbg());
            ste.setInt(4, r.getId_user());
      
            
          
         
            ste.executeUpdate();

            System.out.println("Reclamation Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    */
    
   /* public void ajouterReclamation(Reclamation p){
        String sql="INSERT INTO `Reclamation`(`id`, `nom`, `prenom`) VALUES ('"+p.getId()+"','"+p.getNom()+"','"+p.getPrenom()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reclamation Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        }*/
        
        
   /* 
    public void ajouterReclamation2(Reclamation r){
        String sql="insert into reclamation(`id_rec`, `desc_rec`, `traite`, `dateAjoutrec`, `dateTraitrec`, `id_hbg`, `id_user`) values(?,?,?,?,?,?,?)" ;
                
                
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            
            ste.setInt(1,r.getId_rec());
            ste.setString(1, r.getDesc_rec());
            ste.setInt(2, r.getTraite());
            ste.setDate(1,r.getDateAjoutrec());
            ste.setDate(2,r.getDateTraitrec());
            ste.setInt(3, r.getId_hbg());
            ste.setInt(4, r.getId_user());
           
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
    
    
    
    /*
    public List<Reclamation> afficherReclamation(){
        List<Reclamation> reclamations = new ArrayList<>();
        String query="select * from reclamation";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId_rec(rs.getInt("id_rec"));
                r.setTraite(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                reclamations.add(r);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reclamations;
        
    }
*/
    
     public List<Reclamation> afficherReclamation(){
        List<Reclamation> reclamations = new ArrayList<>();
        String query="select * from reclamation";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId_rec(rs.getInt("id_rec"));
                r.setDesc_rec(rs.getString("desc_rec"));
                r.setTraite(rs.getInt("traite"));
                r.setDateAjoutrec(rs.getDate("dateAjoutrec"));
                r.setDateTraitrec(rs.getDate("dateTraitrec"));
                r.setId_hbg(rs.getInt("id_hbg"));
                r.setId_user(rs.getInt("id_user"));
                reclamations.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reclamations;
        
    }
    
    
    
    public void supprimerReclamation(int id) {
 try {
            String sql = "DELETE FROM reclamation WHERE id_rec="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Reclamation Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
   
    
    
    
}
