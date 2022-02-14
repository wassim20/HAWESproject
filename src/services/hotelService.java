
package services;

import entities.hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

public class hotelService {
    Connection cnx;
    
 
    public hotelService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    //nom_hotel,etoile,nb_chambres,nb_suites,piscine,prix
    public void ajouterPersonne2(hotel l){
        String sql="insert into hotel(nom_hotel,etoile,nb_chambres,nb_suites,piscine,prix) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1, l.getNom_hotel());
            ste.setInt(1, l.getEtoile());
            ste.setInt(2,l.getNb_chambres());
            ste.setInt(3,l.getNb_suites());
            ste.setInt(4,l.getPiscine());
            ste.setInt(5,l.getPrix());
            
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
                //nom_hotel,etoile,nb_chambres,nb_suites,piscine,prix
                hotel h = new hotel();
                h.setNom_hotel(rs.getString("nom_hotel"));
                h.setEtoile(rs.getInt("etoile"));
                h.setNb_chambres(rs.getInt("nb_chambres"));
                h.setNb_suites(rs.getInt("nb_suites"));
                h.setPiscine(rs.getInt("piscine"));
                h.setPrix(rs.getInt("prix"));
                hotels.add(h);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return hotels;
        
    }
    
}
