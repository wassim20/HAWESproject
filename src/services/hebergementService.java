
package services;

import entities.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import tools.MaConnexion;

public class hebergementService {
    Connection cnx;
    
 
    public hebergementService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
    public void ajouterhebergement(hebergement l,int a){
        String sql="insert into hebergement(id_hbg,nom,city,date_ajout,adress,nom_hotel,nb_chambres,nb_suites,piscine,image,prix) values(?,?,?,?,?,?,?,?,?,?,?)";
        String sql1="insert into categorie(id_hbg,etoile) values(?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            PreparedStatement ste1= cnx.prepareStatement(sql1);
            ste.setInt(1,l.getId_hbg());
            ste.setString(2,l.getNom());
            ste.setString(3,l.getCity());
            java.util.Date utilDate = l.getDate_ajout();
             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
             ste.setDate(4, sqlDate);
             ste.setString(5,l.getAdress());
            ste.setString(6, l.getNom_hotel());
            ste.setInt(7,l.getNb_chambres());
            ste.setInt(8,l.getNb_suites());
            ste.setInt(9,l.getPiscine());
            ste.setString(10,l.getImage());
            ste.setInt(11,l.getPrix());
            
            
            ste1.setInt(1,l.getId_hbg());
            ste1.setInt(2,a);
            
            
            ste.executeUpdate();
            ste1.executeUpdate();
            
            System.out.println("Hebergement Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *
     * @return
     */
    public Map<hebergement,Integer> afficherHebergement(){
        Map<hebergement,Integer> hebergements = new HashMap<>();
        String query="select * from hebergement";
        
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            //rs.next();
            while(rs.next()){
                //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
                hebergement h = new hebergement();
                
                h.setId_hbg(rs.getInt("id_hbg"));
                h.setNom(rs.getString("nom"));
                h.setCity(rs.getString("city"));
                h.setDate_ajout(rs.getDate("date_ajout"));
                h.setAdress(rs.getString("adress"));
                
                h.setNom_hotel(rs.getString("nom_hotel"));
                
                h.setNb_chambres(rs.getInt("nb_chambres"));
                h.setNb_suites(rs.getInt("nb_suites"));
                h.setPiscine(rs.getInt("piscine"));
                h.setImage(rs.getString("image"));
                h.setPrix(rs.getInt("prix"));
                
                
                String query1="select * from categorie WHERE id_hbg="+rs.getInt("id_hbg")+"";
                PreparedStatement ste1 =cnx.prepareStatement(query1);
                ResultSet rs1 =ste1.executeQuery();
                rs1.next();

                hebergements.put(h, rs1.getInt("etoile"));
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return hebergements;
        
    }
    
    
    public boolean updatehebergement(hebergement m,int a,int b){
      //  String query="UPDATE hotel h JOIN hebergement he ON h.id_hbg=he.id_hbg SET  nom_hotel='"+m.getNom_hotel()+"',etoile='"+m.getEtoile()+"',nb_chambres='"+m.getNb_chambres()+"',nb_suites='"+m.getNb_suites()+"',piscine='"+m.getPiscine()+"',image='"+m.getImage()+"',prix='"+m.getPrix()+"',city='"+m.getCity()+"',date_ajout='"+m.getDate_ajout()+"',adress='"+m.getAdress()+"' WHERE id_hbg="+a+"";
        Statement st = null;
        

                 //nom_hotel,etoile,nb_chambres,nb_suites,piscine,image,prix
                 //UPDATE hotel h JOIN hebergement he ON h.id_hbg=he.id_hbg SET nom_hotel='haaa' WHERE h.nom_hotel='mouradi'
       //String requette = "UPDATE hotel h JOIN hebergement he ON h.id_hbg=he.id_hbg SET  nom_hotel='"+m.getNom_hotel()+"',etoile='"+m.getEtoile()+"',nb_chambres='"+m.getNb_chambres()+"',nb_suites='"+m.getNb_suites()+"',piscine='"+m.getPiscine()+"',image='"+m.getImage()+"',prix='"+m.getPrix()+"',city='"+m.getCity()+"',date_ajout='"+m.getDate_ajout()+"',adress='"+m.getAdress()+"' WHERE id_hbg="+a+"";
        

                //id_hbg;nom;city;date_ajout;adress;nom_hotel;nb_chambres;nb_suites;piscine;image;prix;
        String query="UPDATE hebergement SET  nom='"+m.getNom()+ ",city='"+m.getCity()+"',date_ajout='"+m.getDate_ajout()+"',nb_chambres='"+m.getNb_chambres()+"',nb_suites='"+m.getNb_suites()+"',piscine='"+m.getPiscine()+"',image='"+m.getImage()+"',prix='"+m.getPrix()+"' WHERE id_hbg="+a+"";
        String query1="UPDATE categorie SET etoile='"+b+"' WHERE id_hbg="+a+"";
        String query2="update hebergement set nom=?, city=?, date_ajout=?, adress=?, nom_hotel=?, nb_chambres=?,nb_suites=?,piscine=?,image=?,prix=?  where id_hbg = ?";
        
        
        try {
            PreparedStatement ste= cnx.prepareStatement(query2);
            PreparedStatement ste1= cnx.prepareStatement(query1);
            /*cnx = MaConnexion.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(query);*/
            
            ste.setString(1,m.getNom());
            ste.setString(2,m.getCity());
            java.util.Date utilDate = m.getDate_ajout();
             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
             ste.setDate(3, sqlDate);
             ste.setString(4,m.getAdress());
            ste.setString(5, m.getNom_hotel());
            ste.setInt(6,m.getNb_chambres());
            ste.setInt(7,m.getNb_suites());
            ste.setInt(8,m.getPiscine());
            ste.setString(9,m.getImage());
            ste.setInt(10,m.getPrix());
            ste.setInt(11,a);
            
                        ste.executeUpdate();
                        
                        ste1.executeUpdate();

            
            
            System.out.println("hebergement updated");
            //st.executeUpdate(query1);
            //System.out.println("categoorie updated");
            return true;
            
           
        } catch (SQLException ex) {
            return false;
        }finally {
           
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
    
    }
    
    
    
    public boolean deletehebergement(hebergement m){
        
        Statement st = null;
        String query="DELETE hebergement,categorie FROM hebergement INNER JOIN categorie ON hebergement.id_hbg = categorie.id_hbg WHERE hebergement.id_hbg ="+m.getId_hbg()+"";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeUpdate();
            System.out.println("hebergement deleted");
            return true;
            
            
           
        } catch (SQLException ex) {
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        
    }

    
    
    
    
}
