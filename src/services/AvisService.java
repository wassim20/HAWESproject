/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
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
public class AvisService {
    Connection cnx;

    public AvisService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

        public void ajouterAvis(Avis a){
        String sql="INSERT INTO `avis`(`id_avis`, `desc_avis`, `etoile` ,`dateAjoutavis`, `id_hbg`, `id_user`) VALUES ('"+a.getId_avis()+"','"+a.getDesc_avis()+"','"+a.getEtoile()+"','"+a.getDateAjoutavis()+"','"+a.getId_hbg()+"','"+a.getId_user()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Reclamation Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
/////////////////////////////
        
public void modifierAvis(Avis a,int id, String desc_avis,int etoile, Date dateAjoutavis ,  int Id_hbg, int Id_user) {
String sql="UPDATE `avis` SET `desc_avis` = '" + desc_avis + "', `etoile` = '" + etoile + "', `dateAjoutavis` = '" + dateAjoutavis + "', `id_hbg` = '" + Id_hbg + "', `id_user` = '" + Id_user + "' WHERE `avis`.`id_avis` = '"+id+"'";        
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Avis Modifieé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//////////////////////////

        
        
    
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
                a.setId_user(rs.getInt("id_user"));
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
   
   
    
    
    
}
