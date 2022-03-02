/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidevv.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidevv.entity.PickUp;
import pidevv.entity.ReservationPickUp;
import pidevv.entity.Vol;
import pidevv.util.DataSource;

/**
 *
 * @author DELL
 */
public class PickUpService implements IService<PickUp>{
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(PickUp pickUp) {
        try {
                
            String requete = "INSERT INTO pickup (idUser, adresseDepart, adresseArrivee, heureDepart, prix) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, pickUp.getIdUser());
            pst.setString(2, pickUp.getAdresseDepart());
            pst.setString(3, pickUp.getAdresseArrivee());
            pst.setString(4, pickUp.getHeureDepart());
            pst.setFloat(5, pickUp.getPrix());
			pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

public List<PickUp> getAllList() {
       List<PickUp> listPickUps = new ArrayList<PickUp>();
       try {
            String requete = "SELECT * FROM pickUp";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listPickUps.add(new PickUp(rs.getInt("id"), rs.getInt("idUser"), rs.getString("adresseDepart"), rs.getString("adresseArrivee"), rs.getString("heureDepart"), rs.getFloat("prix")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listPickUps;
    }

    @Override
    public List<PickUp> getAll() {
           ObservableList <PickUp> listPickUps = FXCollections.observableArrayList();
       try {
            String requete = "SELECT * FROM pickUp";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listPickUps.add(new PickUp(rs.getInt("id"), rs.getInt("idUser"), rs.getString("adresseDepart"), rs.getString("adresseArrivee"), rs.getString("heureDepart"), rs.getFloat("prix")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listPickUps;
    }

    @Override
    public void update(PickUp pickUp) {
        try {
              
            String requete = "UPDATE pickup SET idUser = ?, adresseDepart = ?, adresseArrivee = ?, heureDepart = ?, prix = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, pickUp.getIdUser());
            pst.setString(2, pickUp.getAdresseDepart());
            pst.setString(3, pickUp.getAdresseArrivee());
            pst.setString(4, pickUp.getHeureDepart());
            pst.setFloat(5, pickUp.getPrix());
            pst.setInt(6, pickUp.getId());
			pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    @Override
    public void delete(PickUp pickUp) {
	  try {
            String requete = "DELETE FROM pickup WHERE id=?";
              PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,pickUp.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<PickUp> search(String __input__) {
       ObservableList <PickUp> listPickUps = FXCollections.observableArrayList();
       try {
            String requete = "SELECT * FROM pickUp WHERE adresseDepart LIKE ? OR adresseArrivee LIKE ? OR heureDepart LIKE ? OR prix LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);  
			pst.setString(1, "%"+__input__+"%");
			pst.setString(2, "%"+__input__+"%");
			pst.setString(3, "%"+__input__+"%");
			pst.setString(4, "%"+__input__+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listPickUps.add(new PickUp(rs.getInt("id"), rs.getInt("idUser"), rs.getString("adresseDepart"), rs.getString("adresseArrivee"), rs.getString("heureDepart"), rs.getFloat("prix")));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listPickUps;
    }

    public PickUp getById(int __id) {
		 PickUp pickUp = new PickUp();

        try {
            String requete = "SELECT * FROM PickUp where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setInt(1, __id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pickUp = new PickUp(rs.getInt("id"), rs.getInt("idUser"), rs.getString("adresseDepart"), rs.getString("adresseArrivee"), rs.getString("heureDepart"), rs.getFloat("prix"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return pickUp;
	}
}
