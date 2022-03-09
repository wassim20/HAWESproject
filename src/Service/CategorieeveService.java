/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import entities.categorieeve;
import GUI.Events.AjoutEventController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.getInt;
import Tools.MyConnexion;

/**
 *
 * @author achre
 */
public class CategorieeveService {
    Connection cnx;

    public CategorieeveService() {
        cnx=MyConnexion.getInstance().getCnx();
    }

    /**
     *

     * @param cat
     */
    public void ajouterCategorie( categorieeve cat){
        String sql1="INSERT INTO `categorieeve`(`nomCat`)"+  " VALUES ('"+cat.getNomCat()+"')";
        try {
            Statement ste1 = cnx.createStatement();
            ste1.executeUpdate(sql1);            
            System.out.println("Categorie Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    /**
     *
     * @return
     */
    public List<categorieeve> afficherCategorie(){
        List<categorieeve> categories;
        categories = new ArrayList<>();
        String query="select * from categorieeve";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                categorieeve c = new categorieeve();

                c.setIdCat(rs.getInt("idCat"));
                c.setNomCat(rs.getString("nomCat"));
                categories.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return categories;
        
    }


public void supprimerCategorie(int idCat) {
 try {
            String sql = "DELETE FROM categorieeve WHERE idCat="+idCat+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Categorie Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }    




        public categorieeve getCatById(int i){
            categorieeve c = new categorieeve();
            String query="select * from categorieeve WHERE `idCat` = '" + i +"' ";
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                c.setIdCat(rs.getInt("idCat"));
                c.setNomCat(rs.getString("nomCat"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return c;
        }



public void modifierCategorie
        (categorieeve c,int idCat) {

        try { 
            String sql = "UPDATE categorieeve SET  nomCat=? WHERE idCat=?";
            PreparedStatement ste= cnx.prepareStatement(sql);
            System.out.println(sql);
            
            ste.setInt(2,idCat);
            ste.setString(1,c.getNomCat());
         
            ste.executeUpdate();

            System.out.println("Categorie Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
        
        
        
        public int getCatid(){
            int id = 0;
            try {
                String sql="SELECT idCat from `categorieeve` ";
            
                PreparedStatement ste = cnx.prepareStatement(sql);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                rs.getInt("idCat");
                id=getInt("idCat");                    
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return id;
        }
        
        
}
