
package services;

import entities.maison_dhote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;
public class maisonservice {

    Connection cnx;
    
    public maisonservice() {
        cnx=MaConnexion.getInstance().getCnx();
        
    }
    //nb_chambre,prix
    
        public void ajouterPersonne2(maison_dhote m){
        String sql="insert into maison_dhote(nb_chambres,prix) values(?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setInt(1, m.getNb_chambres());
            ste.setInt(2, m.getPrix());
            ste.executeUpdate();
            System.out.println("maison d'hote Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<maison_dhote> afficherPersonne(){
        List<maison_dhote> maisons = new ArrayList<>();
        String query="select * from maison_dhote";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                maison_dhote m = new maison_dhote();
                m.setNb_chambres(rs.getInt("nb_chambres"));
                m.setPrix(rs.getInt("Prix"));
                
                maisons.add(m);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return maisons;
        
    }
    
    
}
