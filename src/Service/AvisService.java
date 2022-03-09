/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entités.Avis;
import Entités.Reclamation;
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
import Tools.MyConnexion;

/**
 *
 * @author Mohamed
 */
public class AvisService {
    Connection cnx;

    public AvisService() {
        cnx=MyConnexion.getInstance().getCnx();
    }

        public void ajouterAvis(Avis a){
        String sql="INSERT INTO `avis`(`id_avis`, `desc_avis`, `etoile` ,`dateAjoutavis`, `id_hbg`, `idUser`) VALUES ('"+a.getId_avis()+"','"+a.getDesc_avis()+"','"+a.getEtoile()+"',CURRENT_TIMESTAMP(),'"+a.getId_hbg()+"','"+a.getId_user()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("avis Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
/////////////////////////////
        
public void modifierAvis(Avis a,int id) {
String sql="UPDATE `avis` SET `desc_avis` = '" + a.getDesc_avis() + "', `etoile` = '" + a.getEtoile() + "', `dateAjoutavis` = '" + a.getDateAjoutavis() + "', `id_hbg` = '" + a.getId_hbg() + "', `idUser` = '" + a.getId_user() + "' WHERE `avis`.`id_avis` = '"+id+"'";        
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Avis Modifieé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//////////////////////////

        
     public List<Avis> afficherAvis2(){
        List<Avis> aviss = new ArrayList<>();
        String query="select * from avis";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Avis a = new Avis();
                a.setId_avis(rs.getInt("id_avis"));
                a.setDesc_avis(rs.getString("desc_avis"));
                a.setEtoile(rs.getInt("etoile"));
                a.setDateAjoutavis(rs.getDate("dateAjoutavis"));
                a.setId_hbg(rs.getInt("id_hbg"));
                a.setId_user(rs.getInt("idUser"));
                aviss.add(a);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return aviss;
        
    }     
    
  public List<Avis> afficherAvis(){
        List<Avis> aviss = new ArrayList<>();
        String query="select * from avis";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Avis a = new Avis();
                a.setId_avis(rs.getInt("id_avis"));
                a.setDesc_avis(rs.getString("desc_avis"));
                a.setEtoile(rs.getInt("etoile"));
                a.setDateAjoutavis(rs.getDate("dateAjoutavis"));
                a.setId_hbg(rs.getInt("id_hbg"));
                a.setId_user(rs.getInt("idUser"));
                aviss.add(a);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return aviss;
        
        
        
        
    }
    
    
    
    public void supprimerAvis(int id) {
 try {
            String sql = "DELETE FROM avis WHERE id_avis="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("avis Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    public Avis getAvisById(int idAvis) {
        Avis a = new Avis();
            String query="select * from avis WHERE `id_avis` = '" + idAvis +"' ";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                    a.setId_avis(rs.getInt("id_avis"));
                    a.setDesc_avis(rs.getString("desc_avis"));
                    a.setEtoile(rs.getInt("etoile"));
                    a.setDateAjoutavis(rs.getDate("dateAjoutavis"));
                    a.setId_hbg(rs.getInt("id_hbg"));
                    a.setId_user(rs.getInt("idUser"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return a;
    }
    
     public List<Avis> chercherAvis(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<Avis> aavis = new ArrayList<>();
            if(o.getClass()==ch.getClass()){
                ch=(String) o;
                query="SELECT * FROM `avis` WHERE `desc_avis` LIKE '%" + ch + "%' OR `dateAjoutavis` LIKE '%" + ch + "%'";
            }
            if(o instanceof Integer){
                i=(Integer) o;
                query="SELECT * FROM `avis` WHERE `id_avis` = " + i + " OR `idUser` = " + i + " OR `id_hbg` = " + i + " OR `etoile` = " + i + "";
            }
            try {
                System.out.println(query);
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                   Avis a = new Avis();
                a.setId_avis(rs.getInt("id_avis"));
                a.setDesc_avis(rs.getString("desc_avis"));
                a.setEtoile(rs.getInt("etoile"));
                a.setDateAjoutavis(rs.getDate("dateAjoutavis"));
                a.setId_hbg(rs.getInt("id_hbg"));
                a.setId_user(rs.getInt("idUser"));
                aavis.add(a);
                    
                    System.out.println(aavis);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return aavis;   
        }
   
   
    
    
    
}
