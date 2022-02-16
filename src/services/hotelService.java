
package services;

import entities.hotel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

public class hotelService {
    Connection cnx;
    
 
    public hotelService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    //nom_hotel,etoile,nb_chambres,nb_suites,piscine,prix
    public void ajouterhotel(hotel l){
        String sql="insert into hotel(nom_hotel,etoile,nb_chambres,nb_suites,piscine,image,prix,id_hbg) values(?,?,?,?,?,?,?)";
        String sql1="insert into hebergement(id_hbg,city,date_ajout,adress) values(?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            PreparedStatement ste1= cnx.prepareStatement(sql1);
            ste.setString(0, l.getNom_hotel());
            ste.setInt(1, l.getEtoile());
            ste.setInt(2,l.getNb_chambres());
            ste.setInt(3,l.getNb_suites());
            ste.setInt(4,l.getPiscine());
            ste.setString(1,l.getImage());
            ste.setInt(5,l.getPrix());
            ste.setInt(0,l.getId_hbg());
            
            ste1.setInt(0,l.getId_hbg());
            ste1.setString(0,l.getCity());
            ste1.setDate(1, (Date) l.getDate_ajout());
            ste1.setString(1,l.getAdress());
            
            ste1.executeUpdate();
            ste.executeUpdate();
            
            System.out.println("Hotel Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    

    public List<hotel> afficherHotel(){
        List<hotel> hotels = new ArrayList<>();
        String query="select * from hotel";
        
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                //nom_hotel,etoile,nb_chambres,nb_suites,piscine,image,prix
                hotel h = new hotel();
                h.setNom_hotel(rs.getString("nom_hotel"));
                h.setEtoile(rs.getInt("etoile"));
                h.setNb_chambres(rs.getInt("nb_chambres"));
                h.setNb_suites(rs.getInt("nb_suites"));
                h.setPiscine(rs.getInt("piscine"));
                h.setImage(rs.getString("image"));
                h.setPrix(rs.getInt("prix"));
                h.setId_hbg(rs.getInt("id_hbg"));
                
                String query1="select from hebergement WHERE id_hbg="+rs.getInt("id_hbg")+"";
                PreparedStatement ste1 =cnx.prepareStatement(query1);
                ResultSet rs1 =ste1.executeQuery();
                
                h.setCity(rs1.getString("city"));
                h.setDate_ajout(rs1.getDate("date"));
                h.setAdress(rs1.getString("adress"));
                
                hotels.add(h);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return hotels;
        
    }
    
    
    public boolean updatehotel(hotel m){
        
        Statement st = null;

                 //nom_hotel,etoile,nb_chambres,nb_suites,piscine,image,prix
                 //UPDATE hotel h JOIN hebergement he ON h.id_hbg=he.id_hbg SET nom_hotel='haaa' WHERE h.nom_hotel='mouradi'
       String requette = "UPDATE hotel h JOIN hebergement he ON h.id_hbg=he.id_hbg SET  nom_hotel='"+m.getNom_hotel()+"',etoile='"+m.getEtoile()+"',nb_chambres='"+m.getNb_chambres()+"',nb_suites='"+m.getNb_suites()+"',piscine='"+m.getPiscine()+"',image='"+m.getImage()+"',prix='"+m.getPrix()+"',city='"+m.getCity()+"',date_ajout='"+m.getDate_ajout()+"',adress='"+m.getAdress()+"' WHERE id_hbg="+m.getId_hbg()+"";
        try {
            cnx = MaConnexion.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            System.out.println("hotel updated");
            return true;
            
           
        } catch (SQLException ex) {
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored  */}
    }
    }
    
    }
    
    
    
    public boolean deletehotel(hotel m){
        
        Statement st = null;
        String requette = "DELETE FROM hotel h JOIN hebergement he ON h.id_hbg = he.id_hbg  WHERE id_hbg="+m.getId_hbg()+"";
        try {
            cnx = MaConnexion.getInstance().getCnx();
            st = cnx.createStatement();
            st.executeUpdate(requette);
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
