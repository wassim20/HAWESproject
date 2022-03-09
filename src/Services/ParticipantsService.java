/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.evenement;
import entities.participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author achre
 */
public class ParticipantsService {
    
        Connection cnx;

    public ParticipantsService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    
    
    
        public void ajouterParticipant( participants p, evenement e ){
        String sql1="INSERT INTO `participants`(`idUser` , `idPart`, `idEvent`)"+  " VALUES ('"+p.getIdUser()+"','"+p.getIdPart()+"','"+e.getIdEvent()+"')";
        try {
            Statement ste1 = cnx.createStatement();
            ste1.executeUpdate(sql1);            
            System.out.println("Utilisateur Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
    
        public List<participants> afficherParticipant(){
        List<participants> participant;
        participant = new ArrayList<>();
        String query="select * from participants";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                participants p = new participants();

                p.setIdPart(rs.getInt("idPart"));
                p.setIdEvent(rs.getInt("idEvent"));
                p.setIdUser(rs.getInt("idUser"));
                participant.add(p);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return participant;
        
    }


public void supprimerParticipant(int idPart) {
 try {
            String sql = "DELETE FROM participants WHERE idCat="+idPart+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Participant Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }    
    
}
