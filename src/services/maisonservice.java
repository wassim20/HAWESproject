
package services;


import entities.maison_dhote;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;
public class maisonservice {

    Connection cnx;
    
    public maisonservice() {
        cnx=MaConnexion.getInstance().getCnx();
        
    }
    //nb_chambre,image,prix
    
        public void ajoutermaison(maison_dhote l){
        String sql="insert into maison_dhote(nb_chambres,image,prix) values(?,?,?)";
        String sql1="insert into hebergement(id_hbg,city,date_ajout,adress) values(?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            PreparedStatement ste1= cnx.prepareStatement(sql1);
            ste.setInt(1, l.getNb_chambres());
            ste.setString(1,l.getImage());
            ste.setInt(2,l.getPrix());
            
            ste1.setInt(0,l.getId_hbg());
            ste1.setString(0,l.getCity());
            ste1.setDate(1, (Date) l.getDate_ajout());
            ste1.setString(1,l.getAdress());
            
            ste1.executeUpdate();
            ste.executeUpdate();
            System.out.println("maison dhote Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    public List<maison_dhote> affichermaison(){
        List<maison_dhote> maisons = new ArrayList<>();
        String query="select * from maison_dhote";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                //nb_chambre,image,prix
                maison_dhote h = new maison_dhote();
                
                h.setNb_chambres(rs.getInt("nb_chambres"));
                h.setImage(rs.getString("image"));
                h.setPrix(rs.getInt("prix"));
                
                
                String query1="select from hebergement WHERE id_hbg="+rs.getInt("id_hbg")+"";
                PreparedStatement ste1 =cnx.prepareStatement(query1);
                ResultSet rs1 =ste1.executeQuery();
                
                h.setCity(rs1.getString("city"));
                h.setDate_ajout(rs1.getDate("date"));
                h.setAdress(rs1.getString("adress"));
                
                
                
                
                maisons.add(h);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return maisons;
        
    }
    
    
    public boolean updatemaison(maison_dhote m){
        
        Statement st = null;

                //nb_chambre,image,prix
       String requette = "UPDATE maison_dhote h JOIN hebergement he ON h.id_hbg=he.id_hbg SET nb_chambre='"+m.getNb_chambres()+"',image='"+m.getImage()+"',prix='"+m.getPrix()+"',city='"+m.getCity()+"',date_ajout='"+m.getDate_ajout()+"',adress='"+m.getAdress()+"' WHERE id_hbg="+m.getId_hbg()+"";
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
        } catch (SQLException e) { /* Ignored  */}
    }
    }
    
    }
    
    
    
    public boolean deletemaison(maison_dhote m){
        
        Statement st = null;
        String requette = "DELETE FROM maison_dhote h JOIN hebergement he ON h.id_hbg = he.id_hbg WHERE id="+m.getId_hbg()+"";
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
